package com.luke.microservices.cqbsresttesttest.service.impl;

import com.luke.microservices.cqbsresttesttest.dto.response.TaskDto;
import com.luke.microservices.cqbsresttesttest.dto.response.TaskRequestAdd;
import com.luke.microservices.cqbsresttesttest.entity.Task;
import com.luke.microservices.cqbsresttesttest.repository.TaskRepository;
import com.luke.microservices.cqbsresttesttest.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<Task> taskList = taskRepository.findAll();

        if (taskList.isEmpty()) {
            return Collections.singletonList(new TaskDto(null, "There are no items to display"));
        }

        return taskList.stream().map(task -> new TaskDto(task.getId(), task.getName())).toList();
    }

    @Override
    public TaskDto findTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            return new TaskDto(task.getId(), task.getName());
        }

        return new TaskDto(null, "There is no item to display");
    }

    @Override
    public TaskDto saveTask(TaskRequestAdd taskRequestAdd) {
        Task result = taskRepository.save(taskDtoToTask(new TaskDto(null, taskRequestAdd.getTaskName())));

        if (result == null) {
            return new TaskDto(null, "There is no item to display");
        } else {
            return new TaskDto(result.getId(), result.getName());
        }
    }

    @Override
    public TaskDto deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).get();

        if(task.getId() == null){
            return new TaskDto(null, "There is no item to delete");
        } else {
            taskRepository.delete(task);
            return new TaskDto(task.getId(), "Deleted Successfully");
        }
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(taskDto.getTaskId());

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setName(taskDto.getTaskName());
            taskRepository.save(task);

            return new TaskDto(task.getId(), task.getName());
        }

        return null;
    }

    private Task taskDtoToTask(TaskDto taskDto) {

        if (taskDto.getTaskName().isEmpty() || taskDto.getTaskName() == null) {
            throw new IllegalArgumentException("Task name cannot be empty");
        }

        Task task = new Task();
        task.setId(taskDto.getTaskId());
        task.setName(taskDto.getTaskName());

        return task;
    }

}
