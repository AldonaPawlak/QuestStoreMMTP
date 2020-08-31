package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.MentorClass;

public class AddMentorClassDAOImplementation implements AddDAO<MentorClass> {

    DBConnection dbConnection;


    public AddMentorClassDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(MentorClass mentorClass) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO mentors_classes(classes_id, mentors_id) VALUES(%s, %s)", mentorClass.getClassID(),mentorClass.getMentorID()));

    }
}
