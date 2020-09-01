package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Mentor;

public class RemoveMentorDAOImplementation implements RemoveDAO<Mentor> {

    DBConnection dbConnection;

    public RemoveMentorDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM user_details WHERE id = %s;", mentor.getUserDetailsID()));
        dbConnection.ExecuteStatement(String.format("DELETE FROM mentors %s;", mentor.getMentorID()));
    }

}
