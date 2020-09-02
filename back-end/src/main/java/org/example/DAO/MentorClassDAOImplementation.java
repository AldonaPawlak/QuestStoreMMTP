package org.example.DAO;

import org.example.model.MentorClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MentorClassDAOImplementation implements DAO<MentorClass> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public MentorClassDAOImplementation(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(MentorClass mentorClass) {
        dbConnection.executeStatement(String.format("INSERT INTO mentors_classes(classes_id, mentors_id) VALUES('%s', '%s')", mentorClass.getClassID(),mentorClass.getMentorID()));
    }

    @Override
    public void remove(MentorClass mentorClass) {
        dbConnection.executeStatement(String.format("DELETE FROM mentors_classes '%s';", mentorClass.getClassID()));
    }

    @Override
    public void edit(MentorClass mentorClass) {
        dbConnection.executeStatement(String.format("UPDATE mentors_classes SET mentors_id = '%s' WHERE classes_id = '%s';", mentorClass.getMentorID(), mentorClass.getClassID()));
    }

    @Override
    public List<MentorClass> getAll() {
        List<MentorClass> mentorClasses = new ArrayList<>();
        try {
            ResultSet mentorClassesSet = daoGetSet.getDataSet("SELECT * FROM mentors_classes;");
            while (mentorClassesSet.next()) {
                final UUID classID = UUID.fromString(mentorClassesSet.getString("classes_id"));
                final UUID mentorID = UUID.fromString(mentorClassesSet.getString("mentors_id"));
                MentorClass mentorClass = new MentorClass(classID, mentorID);
                mentorClasses.add(mentorClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorClasses;
    }

    @Override
    public MentorClass get(UUID id) {
        return null;
    }
}
