package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.*;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Creep;
import org.example.model.Mentor;
import org.example.model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.UUID;

public class MentorProfileHandler implements HttpHandler {

    DBConnection dbConnection;
    MentorDAO mentorDAO;
    StudentDAO studentDAO;
    CreepDAO creepDAO;

    public MentorProfileHandler() {
        this.dbConnection = new DBConnection();
        this.mentorDAO = new MentorDAO(dbConnection);
        this.studentDAO = new StudentDAO(dbConnection);
        this.creepDAO = new CreepDAO(dbConnection, new DAOGetSet(dbConnection));
    }

    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().put("Access-Control-Allow-Methods", Collections.singletonList("*"));
        exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        String method = exchange.getRequestMethod();
        String response = "";
        System.out.println("Method " + method);
        int status = 200;
        try {
            if (method.equals("POST")) {
                String url = exchange.getRequestURI().getRawPath();
                String[] urlParts = url.split("/");
                System.out.println("id: " + urlParts[3]);
                String id = urlParts[3].replace("user=", "").split("%20")[0];
                System.out.println(id);

                Mentor mentor = mentorDAO.get(UUID.fromString(id));
//                Student student = studentDAO.get(UUID.fromString(id));
//                Creep creep = creepDAO.get(UUID.fromString(id));
//                System.out.println(mentor);
//                System.out.println(student);
//                System.out.println(creep);
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(mentor);
                System.out.println(response);
                sendResponse(response, exchange, status);
                }
            } catch (AbsenceOfRecordsException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(String response, HttpExchange exchange, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }
        exchange.sendResponseHeaders(status, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}