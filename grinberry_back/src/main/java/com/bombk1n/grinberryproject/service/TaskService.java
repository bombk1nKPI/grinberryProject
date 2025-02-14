package com.bombk1n.grinberryproject.service;

import com.bombk1n.grinberryproject.exception.TaskNotFoundException;
import com.bombk1n.grinberryproject.model.dto.TaskDto;
import com.bombk1n.grinberryproject.model.entity.todo.TaskEntity;
import com.bombk1n.grinberryproject.model.entity.todo.TaskType;
import com.bombk1n.grinberryproject.model.entity.user.UserEntity;
import com.bombk1n.grinberryproject.repository.TaskRepository;
import com.bombk1n.grinberryproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(ModelMapper modelMapper, TaskRepository taskRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDto> getAllTasks() {
        UserEntity user = getCurrentUser();
        List<TaskEntity> taskEntities = taskRepository.findByUser(user);
        return taskEntities.stream()
                .map(this::convertTaskEntityToDto)
                .toList();
    }

    public TaskDto getById(Long id) {
        UserEntity user = getCurrentUser();
        return taskRepository.findByIdAndUser(id,user)
                .map(this::convertTaskEntityToDto)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public TaskDto save(TaskDto taskDto) {
        UserEntity user = getCurrentUser();
        TaskEntity taskEntity = convertDtoToTaskEntity(taskDto);
        taskEntity.setCreatedAt(LocalDateTime.now());
        taskEntity.setStatus(TaskType.IN_PROGRESS);
        taskEntity.setUser(user);
        TaskEntity savedTask = taskRepository.save(taskEntity);
        return convertTaskEntityToDto(savedTask);
    }

    public TaskDto update(Long id, TaskDto taskDto) {
        UserEntity user = getCurrentUser();
        TaskEntity existingTask = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setDeadline(taskDto.getDeadline());

        TaskEntity updatedTask = taskRepository.save(existingTask);
        return convertTaskEntityToDto(updatedTask);
    }

    public void delete(Long id) {
        UserEntity user = getCurrentUser();
        TaskEntity existingTask = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(existingTask);
    }

    private UserEntity getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private TaskDto convertTaskEntityToDto(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDto.class);
    }
    private TaskEntity convertDtoToTaskEntity(TaskDto taskDto){
        return modelMapper.map(taskDto, TaskEntity.class);
    }
}
