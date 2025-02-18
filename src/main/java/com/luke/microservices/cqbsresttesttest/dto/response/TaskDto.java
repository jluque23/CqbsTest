package com.luke.microservices.cqbsresttesttest.dto.response;

public class TaskDto {

    public Long taskId;
    public String taskName;

    public TaskDto() {
    }

    public TaskDto(Long taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long id) {
        this.taskId = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
