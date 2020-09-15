package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOGetSet;
import org.example.DAO.DBConnection;
import org.example.DAO.LoginDAO;
import org.example.model.User;
import org.example.model.ValueObject.LoggedUser;
import org.example.services.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.*;

public class LoginHandler implements HttpHandler {

    private LoginDAO loginDAO;
    private ObjectMapper mapper;

    public LoginHandler() {
        DBConnection dbConnection = new DBConnection();
        DAOGetSet daoGetSet = new DAOGetSet(dbConnection);
        this.loginDAO = new LoginDAO(dbConnection, daoGetSet);
        this.mapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        try {
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);

            Map<String, String> data = Parser.parseFormData(br.readLine());
            String email = data.get("email");
            String password = data.get("password");

            User user = loginDAO.login(email, password);
            LoggedUser loggedUser = new LoggedUser(
                    user.getUserDetailsID().toString(),
                    user.getEmail(),
                    user.getClass().getSimpleName()
            );

            HttpCookie cookie = new HttpCookie("user", mapper.writeValueAsString(loggedUser));

            exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

            sendResponse(mapper.writeValueAsString(loggedUser), exchange, 200);

        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(e.getMessage(), exchange, 404);
        }
    }

    private void sendResponse(String response, HttpExchange exchange, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
        }
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        exchange.sendResponseHeaders(status, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
