package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.DAO.ArtifactDAO;
import org.example.DAO.QuestDAO;
import org.example.model.Artifact;
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

class ArtifactHandlerTest {

    ArtifactDAO artifactDAO;
    ArtifactHandler artifactHandler;
    ObjectMapper mapper;
    List<Artifact> availableArtifacts = Arrays.asList(
            new Artifact("name1",  1),
            new Artifact("name2",  2));
    String availableStudentsAsJson = "[{\"name\":\"Ala\",\"email\":\"ala@ala.pl\"}," +
            "{\"name\":\"Ola\",\"email\":\"ola@ola.pl\"}]";
    String availableArtifactAsJson = "[{\"name\":\"name1\",\"price\":\"1\"}," +
            "{\"name\":\"name2\",\"price\":\"2\"}]";
    String availableArtifactAsJson2 = "[{\"name\":\"name12\",\"price\":\"1\"}," +
            "{\"name\":\"name22\",\"price\":\"2\"}]";

    @BeforeEach
    void init(){
        artifactDAO = mock(ArtifactDAO.class);
        mapper = mock(ObjectMapper.class);
        artifactHandler = new ArtifactHandler(artifactDAO, mapper);
    }


    @Test
    void should_displayQuests() throws IOException {
        // Arrange
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);
        doNothing().when(exchange).sendResponseHeaders(200, availableArtifactAsJson.getBytes().length);
        OutputStream os = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(os);
        when(artifactDAO.getAll()).thenReturn(availableArtifacts);
        when(mapper.writeValueAsString(availableArtifacts)).thenReturn(availableArtifactAsJson);

        // Act
        artifactHandler.handle(exchange);

        // Assert
        Assertions.assertAll(
                () -> verify(exchange, times(1)).getRequestMethod(),
                () -> assertNotNull(headers.get("Access-Control-Allow-Methods")),
                () -> verify(exchange).sendResponseHeaders(200, availableArtifactAsJson.getBytes().length),
                () -> verify(os).write(availableArtifactAsJson.getBytes()),
                () -> verify(os).close()
        );
    }

    @Test
    public void should_return_JSON_from_quests() throws JsonProcessingException {
        //given
        when(artifactDAO.getAll()).thenReturn(availableArtifacts);
        when(mapper.writeValueAsString(availableArtifacts)).thenReturn(availableArtifactAsJson);

        //then
        Assertions.assertAll(
                () -> assertEquals(availableArtifactAsJson, artifactHandler.getArtifacts()),
                () -> assertNotEquals(availableArtifactAsJson2, artifactHandler.getArtifacts())
        );
    }

}