package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.Mentor;

public class AddMentorDAOImplementation implements AddDAO<Mentor> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES (%s, %s ,%s ,%s, %s, 2, true);", mentor.getId(), mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO mentors (id, user_details_id) VALUES (%s, %s);", mentor.getMentorID(), mentor.getId()));
    }

}
