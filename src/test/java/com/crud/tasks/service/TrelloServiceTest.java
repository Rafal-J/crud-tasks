package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.createdtrellocard.CreatedTrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void fetchTrelloBoardsTest() {
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //then
        Assert.assertEquals("1", trelloBoardDtos.get(0).getId());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("5", "Zmywanie","http://tinyurl.com");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Zmywanie", "Pościel w 60 stopniach", "top", "12");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("siecwfirmie@gmail.com");

        //when
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);

        //then
        Assert.assertEquals("Zmywanie", trelloCard.getName());

    }

}