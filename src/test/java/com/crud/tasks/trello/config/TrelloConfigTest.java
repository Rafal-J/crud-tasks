package com.crud.tasks.trello.config;

import com.crud.tasks.domain.createdtrellocard.Trello;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        String apiEndpoint = trelloConfig.getTrelloApiEndpoint();
        Assert.assertEquals("https://api.trello.com/1", apiEndpoint);
    }

    @Test
    public void getTrelloAppKey() {
        String key = trelloConfig.getTrelloAppKey();
        Assert.assertEquals("441f2aca7bfda9e50a6b6893527ef36b", key);
    }

    @Test
    public void getTrelloToken() {
        String token = trelloConfig.getTrelloToken();
        Assert.assertEquals("e0f84b12695286961fe0ceef93b9a8924cf4824920a8400d65ba9fda267ea961", token);
    }

    @Test
    public void getTrelloUsername() {
        String username = trelloConfig.getTrelloUsername();
        Assert.assertEquals("rafa963", username);
    }

}