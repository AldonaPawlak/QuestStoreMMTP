package org.example;

import com.sun.net.httpserver.HttpServer;

import org.example.DAO.*;
import org.example.handlers.*;
import org.example.handlers.MentorProfileHandler;
import org.example.handlers.StudentHandler;
import org.example.model.Quest;
import org.example.model.User;

import java.net.InetSocketAddress;
import java.sql.PreparedStatement;

public class App
{
    public static void main( String[] args ) throws Exception {

        DBConnection dbConnection = new DBConnection();
        ArtifactDAO artifactDAO = new ArtifactDAO(dbConnection);
        LoginDAO loginDAO = new LoginDAO(dbConnection);
        MentorDAO mentorDAO = new MentorDAO(dbConnection);
        StudentDAO studentDAO = new StudentDAO(dbConnection);
        DAO<Quest> questDAO = new QuestDAO(dbConnection);
        DAO<User> userDAO = new UserDAO(dbConnection);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/mentor", new MentorHandler(dbConnection, mentorDAO));
        server.createContext("/student", new StudentHandler(dbConnection, studentDAO));
        server.createContext("/mentorView", new MentorProfileHandler(dbConnection, userDAO));
        server.createContext("/login", new LoginHandler(dbConnection, loginDAO));
        server.createContext("/shop", new ArtifactHandler(dbConnection, artifactDAO));
        server.createContext("/quest", new QuestHandler(dbConnection, questDAO));
        server.createContext("/wallet", new WalletHandler(dbConnection, studentDAO, artifactDAO));

        server.setExecutor(null);
        server.start();
        System.out.println("server started");

        }

}