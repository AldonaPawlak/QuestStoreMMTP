package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;

public class AddMentorDAOImplementation implements AddDAO<org.example.model.Mentor> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(org.example.model.Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES (%s, %s ,%s ,%s, %s, 2, true);", mentor.getId(), mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO mentors (id, user_details_id) VALUES (%s, %s);", mentor.getMentorID(), mentor.getId()));
    }

}
