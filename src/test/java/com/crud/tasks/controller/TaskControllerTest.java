package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchtasks() throws Exception {
        //given
        TaskDto taskDto1 = new TaskDto(104L, "Zadanie1", "Pranie1");
        TaskDto taskDto2 = new TaskDto(105L, "Zadanie2", "Pranie2");
        TaskDto taskDto3 = new TaskDto(106L, "Zadanie3", "Pranie3");
        List<TaskDto> taskDtos = new ArrayList<>(Arrays.asList(taskDto1, taskDto2, taskDto3));

        Task task1 = new Task(104L, "Zadanie1", "Pranie1");
        Task task2 = new Task(105L, "Zadanie2", "Pranie2");
        Task task3 = new Task(106L, "Zadanie3", "Pranie3");
        List<Task> tasks = new ArrayList<>(Arrays.asList(task1, task2, task3));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(ArgumentMatchers.anyList())).thenReturn(taskDtos);

        //when@then
        mockMvc.perform(get("/ver1/tasks/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(104)))
                .andExpect(jsonPath("$[1].title", is("Zadanie2")))
                .andExpect(jsonPath("$[2].content", is("Pranie3")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Zadanie1", "Pranie1");
        Optional<Task> optionalTask = Optional.of(new Task(1L, "Zadanie1", "Pranie1"));

        when(taskMapper.mapToDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTaskById(ArgumentMatchers.anyLong())).thenReturn(optionalTask);

        //when@then
        mockMvc.perform(get("/ver1/tasks/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Zadanie1")))
                .andExpect(jsonPath("$.content", is("Pranie1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(104L, "Zadanie1", "Pranie1");
        Task task = new Task(104L, "Zadanie1", "Pranie1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when&then
        mockMvc.perform(post("/ver1/tasks/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Zadanie1", "Pranie1");
        Task task = new Task(1L, "Zadanie1", "Pranie1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when&then
        mockMvc.perform(put("/ver1/tasks/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Zadanie1")))
                .andExpect(jsonPath("$.content", is("Pranie1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
    Optional<Task> task = Optional.of(new Task(41L, "Sprzątanie", "W pokojach dzieci"));
    when(dbService.getTaskById(ArgumentMatchers.anyLong())).thenReturn(task);
    doNothing().when(dbService).deleteTaskById(ArgumentMatchers.anyLong());

    mockMvc.perform(delete("/ver1/tasks/deleteTask?taskId=41").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
 }