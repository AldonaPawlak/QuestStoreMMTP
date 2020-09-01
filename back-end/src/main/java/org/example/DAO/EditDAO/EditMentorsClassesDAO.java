package org.example.DAO.EditDAO;

import org.example.DAO.DBConnection;
import org.example.model.MentorClass;

public class EditMentorsClassesDAO implements EditDAO<MentorClass> {

    DBConnection dbConnection;

    public EditMentorsClassesDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(MentorClass mentorClass) {
        dbConnection.ExecuteStatement(String.format("UPDATE mentors_classes SET mentors_id = %s WHERE classes_id = %s;", mentorClass.getMentorID(), mentorClass.getClassID()));
    }
}