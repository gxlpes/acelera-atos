package com.task.manage.service;

import com.task.manage.model.Task;

import java.util.UUID;

public interface ITaskService {
        Iterable<Task> getAllTasks();

        Task getTaskById(UUID id);

        Task createTask(Task task);

        Task updateTask(UUID id, Task updatedTask);

        void deleteTask(UUID id);


}
