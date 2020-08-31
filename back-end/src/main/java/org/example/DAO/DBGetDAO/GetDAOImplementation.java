package org.example.DAO.DBGetDAO;

import org.example.DAO.DBConnection;
import org.example.DAO.GetDAO;
import org.example.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDAOImplementation implements GetDAO {

    DBConnection dbConnection;

    public GetDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ResultSet getDataSet(String query) {
        dbConnection.Connection();
        try {
            dbConnection.statement = dbConnection.connection.createStatement();
            ResultSet results = dbConnection.statement.executeQuery(query);
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    @Override
    public List<Student> getAllEntries(String query) {
        List<Student> allStudents = new ArrayList<>();
   /*     try {
            ResultSet dataSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            while (dataSet.next()) {
                final String oneEntry = dataSet.getString("entry");
                final String name = dataSet.getString("name");
                final String date = dataSet.getString("date");
                Entry entry = new Entry(oneEntry, name, date);
                allEntries.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return allStudents;
    }
}
