package org.example.DAO;

import org.example.model.Mentor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MentorDAO implements DAO<Mentor> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public MentorDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Mentor mentor) {
        dbConnection.connect();
        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active, phone_number) VALUES (?, ?, ?, ?, ?, ?, true, ?);");
            preparedStatement.setObject(1, mentor.getUserDetailsID(), Types.OTHER);
            preparedStatement.setString(2, mentor.getName());
            preparedStatement.setString(3, mentor.getSurname());
            preparedStatement.setString(4, mentor.getEmail());
            preparedStatement.setString(5, mentor.getPassword());
            preparedStatement.setObject(6, mentor.getRoleID(), Types.OTHER);
            preparedStatement.setString(7, mentor.getPhoneNumber());
            preparedStatement.executeUpdate();
            System.out.println("Added user successfully.");

            PreparedStatement statement = dbConnection.getConnection().prepareStatement("INSERT INTO mentors (mentor_id, user_details_id) VALUES (?, ?);");
            statement.setObject(1, mentor.getMentorID(), Types.OTHER);
            statement.setObject(2, mentor.getUserDetailsID(), Types.OTHER);
            statement.executeUpdate();
            System.out.println("Added mentor successfully.");
            dbConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Adding mentor failed.");
        }
    }

    @Override
    public void remove(Mentor mentor) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("DELETE FROM mentors WHERE mentor_id = ?;");
            preparedStatement.setObject(1, mentor.getMentorID(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Removed mentor successfully.");

            PreparedStatement statement = dbConnection.getConnection().prepareStatement("DELETE FROM user_details WHERE id = ?;");
            statement.setObject(1, mentor.getUserDetailsID(), Types.OTHER);
            statement.executeUpdate();
            System.out.println("Removed user successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Mentor mentor) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE user_details SET name = ?, surname = ?, email = ?, password = ?, is_active = ? WHERE id = ?;");
            preparedStatement.setString(1, mentor.getName());
            preparedStatement.setString(2, mentor.getSurname());
            preparedStatement.setString(3, mentor.getEmail());
            preparedStatement.setString(4, mentor.getPassword());
            preparedStatement.setBoolean(5, mentor.isActive());
            preparedStatement.setObject(6, mentor.getUserDetailsID(), Types.OTHER);
            System.out.println("Mentors data edited successfully.");
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Mentor> getAll() {
        List<Mentor> mentors = new ArrayList<>();
        try {
            ResultSet allMentors = daoGetSet.getDataSet("SELECT * FROM user_details, mentors WHERE user_details.id = mentors.user_details_id;");
            while (allMentors.next()) {
                final UUID userDetailsID = UUID.fromString(allMentors.getString("id"));
                final String name = allMentors.getString("name");
                final String surname = allMentors.getString("surname");
                final String email = allMentors.getString("email");
                final String password = allMentors.getString("password");
                final UUID roleID = UUID.fromString(allMentors.getString("role_id"));
                final UUID mentorID = UUID.fromString(allMentors.getString("mentor_id"));
                final boolean isActive = allMentors.getBoolean("is_active");
                final  String phoneNumber = allMentors.getString("phone_number");
                Mentor mentor = new Mentor(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, mentorID);
                mentors.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }

    @Override
    public Mentor get(UUID id) throws Exception{
        try {
            ResultSet result = daoGetSet.getDataSet(String.format("SELECT * FROM user_details, mentors WHERE user_details.id = mentors.user_details_id AND user_details_id='%s';", id));
            while (result.next()) {
                final String name = result.getString("name");
                final String surname = result.getString("surname");
                final String email = result.getString("email");
                final String password = result.getString("password");
                final UUID roleID = UUID.fromString(result.getString("role_id"));
                final UUID mentorID = UUID.fromString(result.getString("mentor_id"));
                final boolean isActive = result.getBoolean("is_active");
                final  String phoneNumber = result.getString("phone_number");
                Mentor mentor = new Mentor(id, name, surname, email, password, roleID, isActive, phoneNumber, mentorID);
                return mentor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("User not found.");
    }

}
