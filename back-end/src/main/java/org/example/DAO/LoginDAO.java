package org.example.DAO;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.config.PasswordCrypter;
import org.example.model.Mentor;
import org.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LoginDAO {

    DBConnection dbConnection;
    MentorDAO mentorDAO;
    StudentDAO studentDAO;
    CreepDAO creepDAO;

    public LoginDAO(DBConnection dbConnection, MentorDAO mentorDAO, StudentDAO studentDAO, CreepDAO creepDAO) {
        this.mentorDAO = mentorDAO;
        this.studentDAO = studentDAO;
        this.creepDAO = creepDAO;
        this.dbConnection = dbConnection;
    }

    public LoginDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.mentorDAO = new MentorDAO(dbConnection);
        this.studentDAO = new StudentDAO(dbConnection);
        this.creepDAO = new CreepDAO(dbConnection);
    }

    public User login(String email, String password) throws Exception {
//        password = PasswordCrypter.crypter(password);
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "SELECT ud.id, r.name AS role FROM user_details ud LEFT JOIN roles r ON ud.role_id=r.id " +
                            "WHERE ud.email=? AND ud.password = ?;");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet loggedUserData = preparedStatement.executeQuery();
            while (loggedUserData.next()) {
                final UUID id = UUID.fromString(loggedUserData.getString("id"));
                final String role = loggedUserData.getString("role");
                return getUser(id, role);
            }
            dbConnection.disconnect();
            System.out.println("Selected user from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting user from data base failed.");
        }
        throw new AbsenceOfRecordsException();
    }

    private User getUser(UUID id, String role) throws Exception {
        switch (role) {
            case "mentor" :
                return mentorDAO.get(id);
            case "student" :
                return studentDAO.get(id);
            case "creep" :
                return creepDAO.get(id);
            default :
                throw new Exception("Can not create user with " + role + " type");
        }
    }
}