package com.sachin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sachin.entity.Task;
import com.sachin.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task
    		) {
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


@GetMapping("/{id}")
public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
    Task task = taskService.getTaskById(id);
    return ResponseEntity.ok(task);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PutMapping("/{id}")
  public ResponseEntity<Task> updateTask(@Valid @PathVariable Long id, @RequestBody Task updateTask )
  {
    Task  savedTask= 	taskService.updateTask(id, updateTask);
    return ResponseEntity.ok(savedTask);
  }
    
    
    
    @GetMapping("/page")
    public Page<Task> getTasksPage(@PageableDefault(size = 5,sort = "id",  direction = Sort.Direction.ASC ) Pageable pageable) {

        return taskService.getAllTasks(pageable);
    }
    
}

