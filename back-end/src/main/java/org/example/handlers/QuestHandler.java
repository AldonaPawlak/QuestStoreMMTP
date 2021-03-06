package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAO;
import org.example.DAO.DBConnection;
import org.example.model.Quest;

import java.io.IOException;
import java.util.List;

public class QuestHandler implements HttpHandler {

    private DAO<Quest> questDAO;
    ObjectMapper mapper;

    public QuestHandler(DAO<Quest> questDAO, ObjectMapper mapper) {
        this.questDAO = questDAO;
        this.mapper = mapper;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        int status = 200;
        try {
            if (method.equals("GET")) {
                response = getQuests();
                ResponseHelper.sendResponse(response, exchange, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }
    }

    String getQuests() throws JsonProcessingException {
        List<Quest> quests = questDAO.getAll();
        return mapper.writeValueAsString(quests);
    }

}
