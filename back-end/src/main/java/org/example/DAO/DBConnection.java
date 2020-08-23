package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    Connection connection = null;
    Statement statement = null;
    DBBridge dbBridge = new DBBridge("jdbc:postgresql://ec2-54-217-236-206.eu-west-1.compute.amazonaws.com/dfqdddasahi8nl?sslmode=require", "lahabwdfwwaevk", "39b2388bcffdfc7917aef79f445a3a97d284e50182b9f8a0e808c9706591964e");

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
