package com.example.projectManagement.controllers;

import com.example.projectManagement.models.Task;
import com.example.projectManagement.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;

    // GET /tasks - За получаване на списък с всички задачи
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // GET /tasks/{id} - За получаване на информация за конкретна задача
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /tasks - За създаване на нова задача
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Проверка за наличието на съответния user_id и sprint_id може да бъде добавена тук, ако е необходимо
        Task savedTask = taskRepository.save(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // DELETE /tasks/{id} - За изтриване на конкретна задача
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
