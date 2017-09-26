package com.crud.tasks.repository;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTestSuite {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void getOneTaskTest() {
        Task myTask = taskRepository.findById((long)1).orElse(new Task(null, "Test", "Test"));
        Assert.assertTrue(myTask.getId() == 1);

        Task myTask2 = dbService.getTaskById((long)3).orElse(new Task(null, "Prasowanie", "Koszule dziadka"));

        TaskDto myTask4 = new TaskDto(null, "Test", "Test");
        taskController.createTask(myTask4);

        Task myTask5= new Task((long)3, "Prasowanie", "Koszule dziadka");
        taskRepository.save(myTask5);

        Task myTask6= new Task((long)4, "Prasowanie", "Koszule dziadka");
        dbService.saveTask(myTask6);

        TaskDto myTask7= new TaskDto((long)5, "Prasowanie", "Koszule dziadka");
        taskController.updateTask(myTask7);

    }

}
