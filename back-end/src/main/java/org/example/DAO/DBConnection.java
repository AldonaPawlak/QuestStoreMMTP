package org.example.DAO;

import org.example.config.JSONreader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    Connection connection = null;
    Statement statement = null;
    JSONreader reader = new JSONreader();
    DBBridge dbBridge = new DBBridge(reader.JSONread().get("connection"), reader.JSONread().get("user"), reader.JSONread().get("password"));

    public void Connection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(dbBridge.getDBConnection(),
                            dbBridge.getDBName(), dbBridge.getDBPassword());
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void ExecuteStatement(String sql) {
        Connection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        }  catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
        }
        System.out.println("Query executed succesfully");
    }

}
