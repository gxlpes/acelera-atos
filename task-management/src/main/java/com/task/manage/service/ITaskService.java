package com.task.manage.service;

import com.task.manage.model.Task;

import java.util.List;
import java.util.UUID;

public interface ITaskService {
        List<Task> getAllTasks();

        Task getTaskById(UUID id);

        Task createTask(Task task);

        Task updateTask(UUID id, Task updatedTask);

        void deleteTask(UUID id);


}
