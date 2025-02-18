package com.luke.microservices.cqbsresttesttest.service;

import com.luke.microservices.cqbsresttesttest.dto.response.TaskDto;
import com.luke.microservices.cqbsresttesttest.dto.response.TaskRequestAdd;

import java.util.List;

public interface ITaskService {

    List<TaskDto> findAllTasks();

    TaskDto findTaskById(Long id);

    TaskDto saveTask(TaskRequestAdd taskName);

    TaskDto deleteTaskById(Long id);

    TaskDto updateTask(TaskDto taskDto);
}
