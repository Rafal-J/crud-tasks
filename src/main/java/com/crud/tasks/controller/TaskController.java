package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ver1/tasks")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<Task> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(long taskId) {
        return new TaskDto((long)2, "Pranie", "Tylko białe rzeczy");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(long taskId){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "taskDto")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto((long)1, "Zmywanie", "Wstawić do zmywarki");

    }

    @RequestMapping(method = RequestMethod.POST, value = "taskDto")
    public void createTask(TaskDto taskDto) {

    }
}