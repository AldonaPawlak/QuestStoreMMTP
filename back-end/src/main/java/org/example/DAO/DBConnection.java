package org.example.DAO;

import org.example.config.JSONreader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection implements Connect {

    private String DBConnect;
    private String DBUser;
    private String DBPassword;
    public Statement statement;
    private Connection connection;
    JSONreader reader;

    //TODO make a close DB function and add in mentorDAO where sqlInjection is !!!

    public DBConnection() {
        this.reader = new JSONreader();
        this.DBConnect = reader.JSONread().get("connection");
        this.DBUser = reader.JSONread().get("user");
        this.DBPassword = reader.JSONread().get("password");
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(DBConnect,
                            DBUser, DBPassword);
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        try {
            statement.close();
            connection.close();
        }  catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
            e.printStackTrace();
        }
        System.out.println("Connection closed.");
    }

    public void runSqlQuery(String sql) {
        connect();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }  catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
        e.printStackTrace();
        }
        System.out.println("Query executed succesfully");
    }

}
