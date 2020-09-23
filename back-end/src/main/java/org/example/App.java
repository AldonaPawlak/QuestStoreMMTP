package org.example;

import com.sun.net.httpserver.HttpServer;

import org.example.handlers.*;
import org.example.handlers.MentorProfileHandler;
import org.example.handlers.StudentHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/mentor", new MentorHandler());
        server.createContext("/student", new StudentHandler());
        server.createContext("/mentorView", new MentorProfileHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/shop", new ArtifactHandler());
        server.createContext("/quest", new QuestHandler());
        server.createContext("/wallet", new WalletHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("server started");

        }

}