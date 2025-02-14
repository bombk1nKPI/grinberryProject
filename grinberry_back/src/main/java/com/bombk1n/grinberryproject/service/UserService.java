package com.bombk1n.grinberryproject.service;

import com.bombk1n.grinberryproject.model.dto.TaskDto;
import com.bombk1n.grinberryproject.model.dto.UserDto;
import com.bombk1n.grinberryproject.model.entity.user.UserEntity;
import com.bombk1n.grinberryproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getCurrentUser(UserDetails userDetails) {
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setRoles(user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
        List<TaskDto> taskDtos = user.getTasks().stream()
                .map(task -> new TaskDto(task.getId(),task.getTitle(), task.getDescription(), task.getStatus(), task.getCreatedAt(), task.getDeadline()))
                .collect(Collectors.toList());

        userDto.setTasks(taskDtos);

        return userDto;
    }
}
