package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.*;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Creep;
import org.example.model.User;

import java.io.IOException;
import java.util.UUID;

public class MentorProfileHandler implements HttpHandler {

    private DBConnection dbConnection;
    private MentorDAO mentorDAO;
    private StudentDAO studentDAO;
    private CreepDAO creepDAO;

    public MentorProfileHandler(DBConnection dbConnection, MentorDAO mentorDAO, StudentDAO studentDAO, CreepDAO creepDAO) {
        this.dbConnection = dbConnection;
        this.mentorDAO = mentorDAO;
        this.studentDAO = studentDAO;
        this.creepDAO = creepDAO;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        int status = 200;
        try {
            if (method.equals("POST")) {
                String url = exchange.getRequestURI().getRawPath();
                String[] urlParts = url.split("/");
                String id = urlParts[3].replace("user=", "").split("%20")[0];
                Creep creep = creepDAO.get(UUID.fromString(id));


/*                Mentor mentor = mentorDAO.get(UUID.fromString(id));*/
//                Student student = studentDAO.get(UUID.fromString(id));
             /*   User user = creepDAO.get(UUID.fromString(id));*/
//                System.out.println(mentor);
//                System.out.println(student);
//                System.out.println(creep);
                ObjectMapper objectMapper = new ObjectMapper();
                response = objectMapper.writeValueAsString(creep);
                ResponseHelper.sendResponse(response, exchange, status);
                }
            } catch (AbsenceOfRecordsException e) {
            e.printStackTrace();
        }
    }

}
