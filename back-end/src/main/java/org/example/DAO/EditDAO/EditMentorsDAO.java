package org.example.DAO.EditDAO;

import org.example.DAO.DBConnection;
import org.example.model.Mentor;

public class EditMentorsDAO implements EditDAO<Mentor> {

    DBConnection dbConnection;

    public EditMentorsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("UPDATE mentors SET user_details_id = '%s' WHERE id = '%s';", mentor.getUserDetailsID(), mentor.getMentorID()));
    }
}