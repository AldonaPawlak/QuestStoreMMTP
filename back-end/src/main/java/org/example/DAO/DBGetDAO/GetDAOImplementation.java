package org.example.DAO.DBGetDAO;

import org.example.DAO.DBConnection;
import org.example.model.Creep;
import org.example.model.Mentor;
import org.example.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    public List<Mentor> getAllMentors(String query, String secondQuery) {
        List<Mentor> allStudents = new ArrayList<>();
        try {
            ResultSet usersSet = getDataSet(secondQuery);
            ResultSet mentorsSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            UUID id;
            String name;
            String surname;
            String email;
            String password;
            UUID roleID;
            boolean isActive;
            UUID mentorID;
            UUID userDetailsID;
            while (mentorsSet.next()) {
                mentorID = UUID.fromString(mentorsSet.getString("id"));
                userDetailsID = UUID.fromString(mentorsSet.getString("user_details_id"));
                while (usersSet.next()) {
                    if (userDetailsID.equals(usersSet.getString("id"))) {
                        id = UUID.fromString(usersSet.getString("id"));
                        name = usersSet.getString("name");
                        surname = usersSet.getString("surname");
                        email = usersSet.getNString("email");
                        password = usersSet.getString("password");
                        roleID = UUID.fromString(usersSet.getString("role_ID"));
                        isActive = usersSet.getBoolean("is_active");
                        Mentor mentor = new Mentor(id, name, surname, email, password, roleID, isActive, mentorID);
                        allStudents.add(mentor);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public List<Student> getAll(String query, String secondQuery) {
        List<Student> allStudents = new ArrayList<>();
        try {
            ResultSet usersSet = getDataSet(secondQuery);
            ResultSet studentsSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            UUID id;
            String name;
            String surname;
            String email;
            String password;
            UUID roleID;
            boolean isActive;
            UUID studentID;
            UUID userDetailsID;
            int coins;
            while (studentsSet.next()) {
                studentID = UUID.fromString(studentsSet.getString("id"));
                userDetailsID = UUID.fromString(studentsSet.getString("user_details_id"));
                coins = studentsSet.getInt("coins");
                while (usersSet.next()) {
                    if (userDetailsID.equals(usersSet.getString("id"))) {
                        id = UUID.fromString(usersSet.getString("id"));
                        name = usersSet.getString("name");
                        surname = usersSet.getString("surname");
                        email = usersSet.getNString("email");
                        password = usersSet.getString("password");
                        roleID = UUID.fromString(usersSet.getString("role_ID"));
                        isActive = usersSet.getBoolean("is_active");
                        Student student = new Student(id, name, surname, email, password, roleID, studentID, coins, isActive);
                        allStudents.add(student);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    public List<Creep> getAllCreeps(String query, String secondQuery) {
        List<Creep> allCreeps = new ArrayList<>();
        try {
            ResultSet usersSet = getDataSet(secondQuery);
            ResultSet creepsSet = getDataSet(query);
            System.out.println("Got record succesfully.\n");
            UUID id;
            String name;
            String surname;
            String email;
            String password;
            UUID roleID;
            boolean isActive;
            UUID creepID;
            UUID userDetailsID;
            while (creepsSet.next()) {
                creepID = UUID.fromString(creepsSet.getString("id"));
                userDetailsID = UUID.fromString(creepsSet.getString("user_details_id"));
                while (usersSet.next()) {
                    if (userDetailsID.equals(usersSet.getString("id"))) {
                        id = UUID.fromString(usersSet.getString("id"));
                        name = usersSet.getString("name");
                        surname = usersSet.getString("surname");
                        email = usersSet.getNString("email");
                        password = usersSet.getString("password");
                        roleID = UUID.fromString(usersSet.getString("role_ID"));
                        isActive = usersSet.getBoolean("is_active");
                        Creep creep = new Creep(id, name, surname, email, password, roleID, creepID, isActive);
                        allCreeps.add(creep);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCreeps;
    }

}
