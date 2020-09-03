package org.example.controller;

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

public class MentorMenuController implements HttpHandler {



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";

        try {
            DBConnection dbConnection = new DBConnection();
            DAOGetSet daoGetSet = new DAOGetSet(dbConnection);

            MentorDAOImplementation mentorDAO = new MentorDAOImplementation(dbConnection, daoGetSet);

            List<Mentor> mentors = mentorDAO.getAll();


            ObjectMapper mapper = new ObjectMapper();

            response = mapper.writeValueAsString(mentors);
            System.out.println(response);

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
            exchange.sendResponseHeaders(200, response.getBytes().length);
        }
        catch (Exception e){
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }



        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private List<Mentor> getMentors() {
        UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        Mentor jan = new Mentor(uid,"Jan", "Kowałski", "malpa@com.pl", "asdf", uid, true, uid);
        Mentor[] mentors = {jan, jan};
        return Arrays.asList(mentors);
    }
}
