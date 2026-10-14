
package com.sachin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sachin.entity.Task;
import com.sachin.exception.TaskNotFoundException;
import com.sachin.repository.TaskRepository;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                    new TaskNotFoundException("Task not found with id: " + id)
                );
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(
                    "Task not found with id: " + id
            );
        }

        taskRepository.deleteById(id);
    }
    
    
    
    public Task updateTask(Long id, Task updatedTask) {

        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());

        return taskRepository.save(existingTask);
    }
    
    
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
    
    
}
