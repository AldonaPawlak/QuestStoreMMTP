package org.example;

import com.sun.net.httpserver.HttpServer;

import org.example.DAO.DAOGetSet;
import org.example.DAO.DBConnection;
import org.example.DAO.MentorDAO;
import org.example.config.IDgenerator;
import org.example.config.PasswordCrypter;
import org.example.handlers.LoginHandler;
import org.example.handlers.MentorHandler;
import org.example.model.Mentor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.UUID;

public class App
{
    public static void main( String[] args ) throws IOException, Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/mentor", new MentorHandler());
//        server.createContext("/student", new StudentMenuController());
//        server.createContext("/mentor", new MentorMenuController());
       server.createContext("/login", new LoginHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("server started");

/*
        DBConnection con = new DBConnection();
        DAOGetSet set = new DAOGetSet(con);
        MentorDAO dao = new MentorDAO(con, set);*/
    /*    IDgenerator idgen = new IDgenerator();
        UUID id = idgen.UniqueID();
        UUID id2 = idgen.UniqueID();
        PasswordCrypter crypter = new PasswordCrypter();
        String password = crypter.crypter("mentor");
        UUID roleID = UUID.fromString("745792a7-681b-4efe-abdd-ca027678b397");
        Mentor mentorr = new Mentor(id, "Dominik","Starzyk", "dominik.s@o2.pl",password , roleID, true, "773 764 987", id2);
        dao.add(mentor);*/
     /*   UUID u = UUID.fromString("db7e1cce-24e2-4452-a298-bc187ebe2b46");
        Mentor mentor = dao.get(u);
        mentor.setActive(false);
        mentor.setEmail("nowyEmail@.com");
        mentor.setName("Lalalalala");
        dao.edit(mentor);*/
       /* PasswordCrypter crypter = new PasswordCrypter();
        String password = crypter.crypter("creep");
        System.out.println(password);*/
        }

}