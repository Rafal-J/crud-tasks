package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.domain.createdtrellocard.CreatedTrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTest {

    @InjectMocks
    TrelloController trelloController;

    @Mock
    TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoardsTest() {
        //given
        List<TrelloListDto> trelloLists = new ArrayList<>();
            trelloLists.add(new TrelloListDto("1", "test_List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
            trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();

        //then
        Assert.assertEquals(false, trelloBoardDtos.get(0).getLists().get(0).isClosed());
        Assert.assertEquals(1, trelloBoardDtos.size());
    }

    @Test
    public void createTrelloCardtest(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Zakupy", "Kupić prosze", "top", "Random List Id");
        CreatedTrelloCardDto trelloCard = new CreatedTrelloCardDto( "Random List Id", "Zakupy", "http://tinyurl.com");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(trelloCard);

        //when
        CreatedTrelloCardDto myCard = trelloController.createTrelloCard(trelloCardDto);

        //then

        Assert.assertEquals("http://tinyurl.com", myCard.getShortUrl());
    }
}