package org.example.DAO;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Creep;
import org.example.model.Mentor;
import org.example.model.Student;
import org.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

public class UserDAO implements DAO<User> {
    DBConnection dbConnection;

    public UserDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void edit(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(UUID id) throws AbsenceOfRecordsException {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "SELECT ud.*, r.name AS role FROM user_details ud JOIN roles r ON r.id = ud.role_id " +
                            "WHERE ud.id = ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                final UUID userDetailsID = UUID.fromString(result.getString("id"));
                final String name = result.getString("name");
                final String surname = result.getString("surname");
                final String email = result.getString("email");
                final String password = result.getString("password");
                final String role = result.getString("role");
                final boolean isActive = result.getBoolean("is_active");
                final String phoneNumber = result.getString("phone_number");
                final UUID roleID = UUID.fromString(result.getString("role_id"));
                User user;
                switch (role) {
                    case "mentor" :
                        UUID tempID = null;
                        PreparedStatement mentorStatement = dbConnection.getConnection().prepareStatement(
                                "SELECT mentor_id FROM mentors WHERE user_details_id = ?;");
                        mentorStatement.setObject(1, id, Types.OTHER);
                        ResultSet mentorSet = mentorStatement.executeQuery();
                        while (mentorSet.next()) {
                            tempID = UUID.fromString(mentorSet.getString("mentor_id"));
                        }
                        user = new Mentor(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber,
                                role, tempID);
                        break;
                    case "creep" :
                        UUID temporaryID = null;
                        PreparedStatement creepStatement = dbConnection.getConnection().prepareStatement(
                                "SELECT creep_id FROM creeps WHERE user_details_id = ?;");
                        creepStatement.setObject(1, id, Types.OTHER);
                        ResultSet creepSet = creepStatement.executeQuery();
                        while (creepSet.next()) {
                        temporaryID = UUID.fromString(creepSet.getString("creep_id"));
                        }
                        user = new Creep(id, name, surname, email, password, roleID, isActive, phoneNumber,
                                role, temporaryID);
                        break;
                    default :
                        UUID temporarID = null;
                        int coins = 0;
                        PreparedStatement studentStatement = dbConnection.getConnection().prepareStatement(
                                "SELECT coins, student_id FROM students WHERE user_details_id = ?;");
                        studentStatement.setObject(1, id, Types.OTHER);
                        ResultSet resultSet = studentStatement.executeQuery();
                        while (resultSet.next()) {
                            coins += resultSet.getInt("coins");
                            temporarID = UUID.fromString(resultSet.getString("student_id"));
                        }
                        user = new Student(userDetailsID, name, surname, email, password, roleID, isActive,
                                phoneNumber, role, temporarID, coins);
                        break;
                }
                return user;
            }
            dbConnection.disconnect();
            System.out.println("Selected user from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting user from data base failed.");
        }
        throw new AbsenceOfRecordsException();
    }

}
