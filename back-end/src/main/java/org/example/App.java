package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;

import org.example.DAO.*;
import org.example.handlers.*;
import org.example.handlers.ProfileHandler;
import org.example.handlers.StudentHandler;

import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception {

        DBConnection dbConnection = new DBConnection();
        ArtifactDAO artifactDAO = new ArtifactDAO(dbConnection);
        LoginDAO loginDAO = new LoginDAO(dbConnection);
        MentorDAO mentorDAO = new MentorDAO(dbConnection);
        StudentDAO studentDAO = new StudentDAO(dbConnection);
        QuestDAO questDAO = new QuestDAO(dbConnection);
        UserDAO userDAO = new UserDAO(dbConnection);
        ObjectMapper mapper = new ObjectMapper();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/mentor", new MentorHandler(dbConnection, mentorDAO, userDAO));
        server.createContext("/student", new StudentHandler(studentDAO, userDAO, mapper));
        server.createContext("/mentorView", new ProfileHandler(dbConnection, userDAO));
        server.createContext("/login", new LoginHandler(dbConnection, loginDAO));
        server.createContext("/shop", new ArtifactHandler(artifactDAO, mapper));
        server.createContext("/quest", new QuestHandler(questDAO, mapper));
        server.createContext("/wallet", new WalletHandler(dbConnection, studentDAO, artifactDAO));

        server.setExecutor(null);
        server.start();
        System.out.println("server started");

        }

}