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
                    "SELECT ud.*, r.name AS role FROM user_details ud JOIN roles r ON r.id = ud.role_id WHERE ud.id ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                final UUID userDetailsID = UUID.fromString(result.getString("id"));
                final String name = result.getString("name");
                final String surname = result.getString("surname");
                final String email = result.getString("email");
                final String password = result.getString("password");
                final UUID roleID = UUID.fromString(result.getString("role_id"));
                final String role = result.getString("role");
                final boolean isActive = result.getBoolean("is_active");
                final String phoneNumber = result.getString("phone_number");
                User user;
                switch (role) {
                    case "mentor" :
                        final UUID detailsID = UUID.fromString(result.getString("id"));
                        user = new Mentor(id, name, surname, email, password, roleID, isActive, phoneNumber, detailsID);
                        break;
                    case "creep" :
                        user = new Creep(id, name, surname, email, password, roleID, isActive, phoneNumber, mentorID);
                        break;
                    default :
                        final int coins = result.getInt("coins");
                        final UUID studentID = UUID.fromString(result.getString("student_id"));
                        user = new Student(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber,
                                studentID, coins);
                        break;
                }
                return user;
            }
            dbConnection.disconnect();
            System.out.println("Selected student from data base successfully.");
        } catch (SQLException e) {
            System.out.println("Selecting student from data base failed.");
            e.printStackTrace();
        }
        throw new AbsenceOfRecordsException();
    }


}
