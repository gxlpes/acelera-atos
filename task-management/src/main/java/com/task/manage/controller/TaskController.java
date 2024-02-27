package com.task.manage.controller;

import com.task.manage.model.Task;
import com.task.manage.repository.TaskRepository;
import com.task.manage.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/form")
    public String showSignUpForm(Task task) {
        return "form";
    }

    @GetMapping
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("task", new Task());
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getTaskById(@PathVariable UUID id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "redirect:/";
    }

    @PostMapping("/tasks")
    public String createTask(Task task) {
        taskService.createTask(task);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable UUID id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PostMapping("/update/{id}")
    public String editTask(@PathVariable("id") UUID id, Task task) {
        taskService.updateTask(task.getId(), task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable UUID id, Model model) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

}

