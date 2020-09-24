package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.*;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.User;

import java.io.IOException;
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
        this.creepDAO = new CreepDAO(dbConnection);
    }

    public void handle(HttpExchange exchange) throws IOException {
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

/*                Mentor mentor = mentorDAO.get(UUID.fromString(id));*/
//                Student student = studentDAO.get(UUID.fromString(id));
                User user = creepDAO.get(UUID.fromString(id));
//                System.out.println(mentor);
//                System.out.println(student);
//                System.out.println(creep);
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(user);
                System.out.println(response);
                ResponseHelper.sendResponse(response, exchange, status);
                }
            } catch (AbsenceOfRecordsException e) {
            e.printStackTrace();
        }
    }

}
