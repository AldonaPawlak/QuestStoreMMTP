package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOGetSet;
import org.example.DAO.DBConnection;
import org.example.DAO.MentorDAOImplementation;
import org.example.model.Mentor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MentorController implements HttpHandler {





    @Override
    public void handle(HttpExchange exchange) throws IOException {

//        exchange.getResponseHeaders().put("Access-Control-Allow-Methods", Collections.singletonList("*"));
//        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        String method = exchange.getRequestMethod();
        String response = "";
        System.out.println("Method " + method);

        try {
            if(method.equals("GET")) {
                    response = getMentors();
                    System.out.println(response);

                    exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    }
            if(method.equals("POST")) {
                exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
                String url = exchange.getRequestURI().getRawPath();
                String[] urlParts = url.split("/");
                String userDetailsID = urlParts[2];
                removeMentor(userDetailsID);
                response = getMentors();
                exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));

                exchange.sendResponseHeaders(200, response.length());
            }



        }
        catch (Exception e){
            e.printStackTrace();
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }



    private String getMentors() throws JsonProcessingException {
        DBConnection dbConnection = new DBConnection();
        DAOGetSet daoGetSet = new DAOGetSet(dbConnection);

        MentorDAOImplementation mentorDAO = new MentorDAOImplementation(dbConnection, daoGetSet);

        List<Mentor> mentors = mentorDAO.getAll();


        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(mentors);
    }

    private void removeMentor(String userDetailsID) {
        DBConnection dbConnection = new DBConnection();
        DAOGetSet daoGetSet = new DAOGetSet(dbConnection);

        MentorDAOImplementation mentorDAO = new MentorDAOImplementation(dbConnection, daoGetSet);

        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));

        mentorDAO.remove(mentor);

    }


}
