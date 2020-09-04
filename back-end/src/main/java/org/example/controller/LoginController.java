package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.*;

public class LoginController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // retrieve data
   /*     InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        Map<String, String> data = parseFormData(br.readLine());
        String email = data.get("email");
        String password = data.get("password");

        //TODO zrób sobie w DAO taka metodę, która zwróci Ci użytkownika na podstawie
        // pola email oraz password, tego użytkowniak zapisz do obiektu User user.

        // Mając użytkownika zwórconego wiemy, że taki wpis w bazie istnieje,
        // wiec możemy utworzyć sesję temu userów wysyłając na front Cookie

        cookie = new HttpCookie("user", email);
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

        response = "User authenticated";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();*/
    }

}
