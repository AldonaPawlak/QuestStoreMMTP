package org.example.DAO;

import org.example.model.Mentor;
import org.example.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MentorDAOI implements DAO<Mentor> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public MentorDAOI(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Mentor mentor) {
        dbConnection.executeStatement(String.format("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES ('%s', '%s' ,'%s' ,'%s', '%s', 2, true);", mentor.getUserDetailsID(), mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword()));
        dbConnection.executeStatement(String.format("INSERT INTO mentors (id, user_details_id) VALUES ('%s', '%s');", mentor.getMentorID(), mentor.getUserDetailsID()));
    }

    @Override
    public void remove(Mentor mentor) {
        dbConnection.executeStatement(String.format("DELETE FROM mentors '%s';", mentor.getMentorID()));
        dbConnection.executeStatement(String.format("DELETE FROM user_details WHERE id = '%s';", mentor.getUserDetailsID()));
    }

    @Override
    public void edit(Mentor mentor) {
        dbConnection.executeStatement(String.format("UPDATE mentors SET user_details_id = '%s' WHERE id = '%s';", mentor.getUserDetailsID(), mentor.getMentorID()));
        dbConnection.executeStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword(), mentor.getRoleID(), mentor.isActive(), mentor.getUserDetailsID()));
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
    public Mentor get(UUID id) {
        List<Mentor> mentors = new ArrayList<>();
        try {
            ResultSet allMentors = daoGetSet.getDataSet(String.format("SELECT * FROM user_details, mentors WHERE user_details.id = mentors.user_details_id AND user_details_id='%s';", id));
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
        return mentors.get(0);
    }

}
