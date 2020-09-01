package org.example.DAO.DBGetDAO;

import org.example.DAO.DBConnection;
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
    public List<?> getAll(String query) {
        return null;
    }

/*    @Override
    public List<Student> getAll(String query, String secondQuery) {
        List<Student> allStudents = new ArrayList<>();
        try {
            ResultSet usersSet = getDataSet(secondQuery);
            ResultSet studentsSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            while (dataSet.next()) {
                final String oneEntry = dataSet.getString("entry");
                final String name = dataSet.getString("name");
                final String date = dataSet.getString("date");
                Student student = new Student(UUID id, String name, String surname, String email, String password, UUID roleID, UUID studentID, UUID userDetailsID, int coins, boolean isActive);
                allStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStudents;
    }*/
}
