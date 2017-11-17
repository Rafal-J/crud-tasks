package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;


    @Test
    public void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("Sprzątanie", "Umyć okna", "top", "randomId");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        Assert.assertTrue(trelloCardDto.getName().equals(trelloCard.getName()));
        Assert.assertTrue(trelloCardDto.getDescription().equals(trelloCard.getDescription()));
        Assert.assertTrue(trelloCardDto.getPos().equals(trelloCard.getPos()));
        Assert.assertTrue(trelloCardDto.getListId().equals(trelloCard.getListId()));
    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Praca domowa", "Odrobić polski", "top", "randomId");
        System.out.println(trelloCardDto);
        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        Assert.assertTrue(trelloCard.getName().equals(trelloCardDto.getName()));
        Assert.assertTrue(trelloCard.getDescription().equals(trelloCardDto.getDescription()));
        Assert.assertTrue(trelloCard.getPos().equals(trelloCardDto.getPos()));
        Assert.assertTrue(trelloCard.getListId().equals(trelloCardDto.getListId()));
    }

    @Test
    public void mapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("Random Id", "Moja lista 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("Random Id", "Moja lista 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("Random Id", "Moja lista 3", true);

        List<TrelloListDto> trelloListDtos = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto2, trelloListDto3));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //then
        Assert.assertEquals(3, trelloLists.size());
        Assert.assertEquals("Moja lista 2", trelloLists.get(1).getName());
    }

    @Test
    public void mapToListDto() {
        //given
        TrelloList trelloList1 = new TrelloList("Random Id", "Moja lista 1", false);
        TrelloList trelloList2 = new TrelloList("Random Id", "Moja lista 2", false);
        TrelloList trelloList3 = new TrelloList("Random Id", "Moja lista 3", true);

        List<TrelloList> trelloLists = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList2, trelloList3));

        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //then
        Assert.assertEquals(3, trelloListDtos.size());
        Assert.assertEquals(true, trelloLists.get(2).getIsClosed());
    }

    @Test
    public void mapToBoard() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("Random Id", "Moja lista 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("Random Id", "Moja lista 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("Random Id", "Moja lista 3", true);

        List<TrelloListDto> trelloListDtos = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto2, trelloListDto3));

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Random Board Id", "Moja tablica 1", trelloListDtos);

        //when
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);

        //then
        Assert.assertEquals("TrelloList", trelloBoard.getLists().get(0).getClass().getSimpleName());
        Assert.assertEquals(3, trelloBoard.getLists().size());
        Assert.assertTrue(trelloBoard.getId().equals("Random Board Id"));

    }

    @Test
    public void mapToBoards() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("Random Id", "Moja lista 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("Random Id", "Moja lista 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("Random Id", "Moja lista 3", true);

        List<TrelloListDto> trelloListDtos1 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto2, trelloListDto3));
        List<TrelloListDto> trelloListDtos2 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto1, trelloListDto1, trelloListDto1, trelloListDto2, trelloListDto3));
        List<TrelloListDto> trelloListDtos3 = new ArrayList<TrelloListDto>(Arrays.asList(trelloListDto2, trelloListDto3));

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Random Board Id 1", "Moja tablica 1", trelloListDtos1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("Random Board Id 2", "Moja tablica 2", trelloListDtos2);
        TrelloBoardDto trelloBoardDto3 = new TrelloBoardDto("Random Board Id 3", "Moja tablica 3", trelloListDtos3);

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<TrelloBoardDto>(Arrays.asList(trelloBoardDto1, trelloBoardDto2, trelloBoardDto3));

        //when
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //then
        Assert.assertEquals(3, trelloBoards.size());
        Assert.assertEquals("Moja tablica 1", trelloBoards.get(0).getName());
        Assert.assertEquals(5, trelloBoards.get(1).getLists().size());
}
    @Test
    public void mapToBoardsDto() {
        //given
        TrelloList trelloList1 = new TrelloList("Random Id", "Moja lista 1", false);
        TrelloList trelloList2 = new TrelloList("Random Id", "Moja lista 2", false);
        TrelloList trelloList3 = new TrelloList("Random Id", "Moja lista 3", true);

        List<TrelloList> trelloLists1 = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList2, trelloList3));
        List<TrelloList> trelloLists2 = new ArrayList<TrelloList>(Arrays.asList(trelloList1, trelloList1, trelloList1, trelloList2, trelloList3));
        List<TrelloList> trelloLists3 = new ArrayList<TrelloList>(Arrays.asList(trelloList2, trelloList3));

        TrelloBoard trelloBoard1 = new TrelloBoard("Random Board Id 1", "Moja tablica 1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("Random Board Id 2", "Moja tablica 2", trelloLists2);
        TrelloBoard trelloBoard3 = new TrelloBoard("Random Board Id 3", "Moja tablica 3", trelloLists3);

        List<TrelloBoard> trelloBoards = new ArrayList<TrelloBoard>(Arrays.asList(trelloBoard1, trelloBoard2, trelloBoard3));

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //then
        Assert.assertEquals(true, trelloBoardDtos.get(0).getLists().get(2).isClosed());
        Assert.assertEquals(3, trelloBoards.size());
    }
}