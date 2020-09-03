package org.example;

import com.sun.net.httpserver.HttpServer;

import org.example.controller.MentorController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8050), 0);
        server.createContext("/mentor", new MentorController());
//        server.createContext("/student", new StudentMenuController());
//        server.createContext("/mentor", new MentorMenuController());
//        server.createContext("/login", new LoginController());

        server.setExecutor(null);
        server.start();
        System.out.println("server started");


    }
}