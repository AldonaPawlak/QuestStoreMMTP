package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.DAO.QuestDAO;
import org.example.model.Quest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class QuestHandlerTest {


    @Test
    void should_displayQuests_when_Logged() {

        // Arrange
        HttpExchange exchange = mock(HttpExchange.class);
        QuestDAO questDAO = mock(QuestDAO.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Quest> questsExpected = Arrays.asList(new Quest(UUID.fromString("11111111-1111-1111-1111-111111111111"), "name1", "description1", 1),
                new Quest(UUID.fromString("22222222-2222-2222-2222-222222222222"), "name2", "description2", 2));
        String expected = "[{\"id\":\"11111111-1111-1111-1111-111111111111\",\"name\":\"name1\",\"description\":\"description1\",\"value\":1}," +
                "{\"id\":\"22222222-2222-2222-2222-222222222222\",\"name\":\"name2\",\"description\":\"description2\",\"value\":2}]";

        // Act
        when(exchange.getRequestMethod()).thenReturn("GET");
        when(questDAO.getAll()).thenReturn(questsExpected);

        // Assert
        Assertions.assertAll(
                () -> assertEquals(exchange.getRequestMethod(), "GET", "should return the right method"),
                () -> assertThat(questDAO.getAll(), is(equalTo(questsExpected))),
                () -> assertEquals(expected, mapper.writeValueAsString(questsExpected), "should return the right string")
        );

    }

}