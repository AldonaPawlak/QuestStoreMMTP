package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.DAO.QuestDAO;
import org.example.model.Quest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestHandlerTest {

    QuestDAO questDAO;
    QuestHandler questHandler;
    ObjectMapper mapper;
    List<Quest> availableQuests = Arrays.asList(
            new Quest(UUID.fromString("11111111-1111-1111-1111-111111111111"), "name1", "description1", 1),
            new Quest(UUID.fromString("22222222-2222-2222-2222-222222222222"), "name2", "description2", 2));
    String availableQuestsAsJson = "[{\"id\":\"11111111-1111-1111-1111-111111111111\",\"name\":\"name1\",\"description\":\"description1\",\"value\":1}," +
            "{\"id\":\"22222222-2222-2222-2222-222222222222\",\"name\":\"name2\",\"description\":\"description2\",\"value\":2}]";
    String availableQuestsAsJson2 = "[{\"id\":\"33111111-1111-1111-1111-111111111111\",\"name\":\"name1\",\"description\":\"description1\",\"value\":1}," +
            "{\"id\":\"22222222-2222-2222-2222-222222222222\",\"name\":\"name2\",\"description\":\"description2\",\"value\":2}]";
    @BeforeEach
    void init(){
        questDAO = mock(QuestDAO.class);
        mapper = mock(ObjectMapper.class);
        questHandler = new QuestHandler(questDAO, mapper);
    }


    @Test
    void should_displayQuests() throws IOException {
        // Arrange
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);
        doNothing().when(exchange).sendResponseHeaders(200, availableQuestsAsJson.getBytes().length);
        OutputStream os = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(os);
        when(questDAO.getAll()).thenReturn(availableQuests);
        when(mapper.writeValueAsString(availableQuests)).thenReturn(availableQuestsAsJson);

        // Act
        questHandler.handle(exchange);

        // Assert
        Assertions.assertAll(
                () -> verify(exchange, times(1)).getRequestMethod(),
                () -> assertNotNull(headers.get("Access-Control-Allow-Methods")),
                () -> verify(exchange).sendResponseHeaders(200, availableQuestsAsJson.getBytes().length),
                () -> verify(os).write(availableQuestsAsJson.getBytes()),
                () -> verify(os).close()
        );
    }

    @Test
    public void should_return_JSON_from_quests() throws JsonProcessingException {
        //given
        when(questDAO.getAll()).thenReturn(availableQuests);
        when(mapper.writeValueAsString(availableQuests)).thenReturn(availableQuestsAsJson);

        //then
        Assertions.assertAll(
                () -> assertEquals(availableQuestsAsJson, questHandler.getQuests()),
                () -> assertNotEquals(availableQuestsAsJson2, questHandler.getQuests())
        );

    }

}
