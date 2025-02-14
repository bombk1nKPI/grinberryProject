package com.bombk1n.grinberryproject.repository;

import com.bombk1n.grinberryproject.model.entity.todo.TaskEntity;
import com.bombk1n.grinberryproject.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser(UserEntity user);
    Optional<TaskEntity> findByIdAndUser(Long id, UserEntity user);
}
