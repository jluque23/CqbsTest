package com.luke.microservices.cqbsresttesttest.controller;

import com.luke.microservices.cqbsresttesttest.dto.response.TaskDto;
import com.luke.microservices.cqbsresttesttest.dto.response.TaskRequestAdd;
import com.luke.microservices.cqbsresttesttest.service.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks Management API",
        description = "API Rest made with SpringBoot that is exposing endpoits to gestionate a list of tasks")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    //    Implementar un endpoint GET /tasks para listar todas las tareas.
    @Operation(summary = "Listing All the tasks added", description = "returns a List of Tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve Lists"),
            @ApiResponse(responseCode = "200", description = "There are no items to display")
    })
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @Operation(summary = "Listing a task filtering by ID", description = "returns a single of Task")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long id) {
        if (taskService.findTaskById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(taskService.findTaskById(id));
        }
    }

    //    Implementar un endpoint POST /tasks para crear una nueva tarea.
    @Operation(summary = "Adding a new Task to the DB", description = "Adding a task")
    @PostMapping()
    public ResponseEntity<String> addTask(@RequestBody TaskRequestAdd taskRequestAdd) {
        TaskDto result = taskService.saveTask(taskRequestAdd);

        if (result.getTaskId() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok("Task added successfully: " + result.getTaskName());
        }
    }

    //    Implementar un endpoint DELETE /tasks/{id} para eliminar una tarea.
    @Operation(summary = "Deleting a task", description = "Deleting a task By TaskID")
    @DeleteMapping()
    public ResponseEntity<String> deleteTask(@RequestParam long id) {
        TaskDto result = taskService.deleteTaskById(id);

        if(result.getTaskId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok("Task deleted successfully: ");
        }
    }

    //    Implementar un endpoint PUT /tasks/{id} para actualizar una tarea.
    @Operation(summary = "Modifying a task by ID", description = "Modifying a task by ID.")
    @PutMapping()
    public ResponseEntity<String> updateTask(@RequestBody TaskDto taskDto) {
        TaskDto result = taskService.updateTask(taskDto);

        if (result.getTaskId() == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok("Task Updated succesfully with TaskId:" + result.getTaskId());
        }
    }

}
