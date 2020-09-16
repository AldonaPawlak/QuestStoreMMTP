package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAOGetSet;
import org.example.DAO.DBConnection;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.DAO.MentorDAO;
import org.example.config.PasswordCrypter;
import org.example.model.Mentor;
import org.example.model.User;
import org.example.services.DecoderURL;
import org.example.services.Parser;

import java.io.IOException;
import java.io.OutputStream;
import java.security.CryptoPrimitive;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MentorHandler implements HttpHandler {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;
    MentorDAO mentorDAO;

    public MentorHandler() {
        this.dbConnection = new DBConnection();
        this.daoGetSet = new DAOGetSet(dbConnection);
        this.mentorDAO = new MentorDAO(dbConnection, daoGetSet);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().put("Access-Control-Allow-Methods", Collections.singletonList("*"));
        exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
        exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        String method = exchange.getRequestMethod();
        String response = "";
        System.out.println("Method " + method);
        int status = 200;
        try {
            if (method.equals("GET")) {
                response = getMentors();
                System.out.println(response);
                sendResponse(response, exchange, status);
            }
            if (method.equals("POST")) {
                String url = exchange.getRequestURI().getRawPath();
                String[] urlParts = url.split("/");
                String userDetailsID = urlParts[2];
                if(urlParts[3].equals("remove")) {
                    removeMentor(userDetailsID);
                }
                if(urlParts[3].equals("edit-name")){
                    editMentorName(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if(urlParts[3].equals("edit-surname")){
                    editMentorSurname(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if(urlParts[3].equals("edit-mail")){
                    editMentorMail(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if(urlParts[3].equals("edit-phone")){
                    editMentorPhone(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if(urlParts[3].equals("add")){
                    addMentor();
                }
                response = getMentors();
                sendResponse(response, exchange, status);
                System.out.println("New response: " + response);}
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }
    }




    private String getMentors() throws JsonProcessingException {
        List<Mentor> mentors = mentorDAO.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(mentors);
    }

    private void removeMentor(String userDetailsID) throws Exception {
        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));
        mentorDAO.remove(mentor);
    }

    private void editMentorName(String userDetailsID, String newName) throws Exception {
        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));
        mentor.setName(newName);
        mentorDAO.edit(mentor);
    }

    private void editMentorSurname(String userDetailsID, String newSurname) throws AbsenceOfRecordsException {
        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));
        mentor.setSurname(newSurname);
        mentorDAO.edit(mentor);
    }

    private void editMentorMail(String userDetailsID, String newMail) throws AbsenceOfRecordsException {
        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));
        mentor.setEmail(newMail);
        mentorDAO.edit(mentor);
    }

    private void editMentorPhone(String userDetailsID, String newPhone) throws AbsenceOfRecordsException {
        Mentor mentor = mentorDAO.get(UUID.fromString(userDetailsID));
        mentor.setPhoneNumber(newPhone);
        System.out.println("!!!!!!!" + mentor.getPhoneNumber());
        System.out.println("!!!22222!" + newPhone);

        mentorDAO.edit(mentor);
    }

    private void addMentor() {
        Mentor mentor = new Mentor(UUID.randomUUID(), "Name", "Surname", "mail@mail.com", PasswordCrypter.crypter("password"), UUID.fromString("745792a7-681b-4efe-abdd-ca027678b397"), true, "444 222 000", UUID.randomUUID());
        mentorDAO.add(mentor);
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
