package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOGetSet;
import org.example.DAO.DBConnection;
import org.example.DAO.QuestDAO;
import org.example.model.Quest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public class QuestHandler implements HttpHandler {

    DBConnection dbConnection;
    QuestDAO questDAO;

    public QuestHandler() {
        this.dbConnection = new DBConnection();
        this.questDAO = new QuestDAO(dbConnection);
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        System.out.println("Method " + method);
        int status = 200;
        try {
            if (method.equals("GET")) {
                response = getQuests();
                System.out.println(response);
                ResponseHelper.sendResponse(response, exchange, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }
    }

    private String getQuests() throws JsonProcessingException {
        List<Quest> quests = questDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(quests);
    }

}
