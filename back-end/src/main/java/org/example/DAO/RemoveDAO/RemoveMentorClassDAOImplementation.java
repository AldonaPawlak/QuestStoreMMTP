package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.MentorClass;

public class RemoveMentorClassDAOImplementation implements RemoveDAO<MentorClass> {

    DBConnection dbConnection;


    public RemoveMentorClassDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(MentorClass mentorClass) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM mentors_classes '%s';", mentorClass.getClassID()));
    }
}
