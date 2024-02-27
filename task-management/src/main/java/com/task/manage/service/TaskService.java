package com.task.manage.service;


import com.task.manage.model.Task;
import com.task.manage.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElse(null);
    }

    public Task createTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        taskRepository.save(task);
        return task;
    }

    public Task updateTask(UUID id, Task updatedTask) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Task existingTask = getTaskById(id);
        if (existingTask == null) {
            throw new IllegalArgumentException("Task not found");
        }
        existingTask.setName(updatedTask.getName());
        existingTask.setDescription(updatedTask.getDescription());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        taskRepository.deleteById(id);
    }

}