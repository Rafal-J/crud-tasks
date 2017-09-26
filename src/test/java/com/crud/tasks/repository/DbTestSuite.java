package com.crud.tasks.repository;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTestSuite {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DbService dbService;

    @Autowired
    private TaskController taskController;

    @Test
    public void getOneTaskTest() {
        List<Task> myTask = taskRepository.findById((long)1);
        Assert.assertTrue(myTask.size() == 1);

        Task myTask2 = dbService.getOneTaskById((long)1);
        Assert.assertTrue(myTask2.getTitle().equals("Pranie"));

        TaskDto myTask3 = taskController.getTask((long)1);
        Assert.assertTrue(myTask3.getId() == 1);

    }

}
