package org.example.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOGetSet {

    DBConnection dbConnection;

    public DAOGetSet(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public ResultSet getDataSet(String query) {
        dbConnection.connection();
        try {
            dbConnection.statement = dbConnection.connection.createStatement();
            ResultSet results = dbConnection.statement.executeQuery(query);
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
