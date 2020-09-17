package org.example.DAO;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassDAO implements DAO<Class>{

    DBConnection dbConnection;

    public ClassDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Class claass) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO classes (id, name) VALUES (?, ?);");
            preparedStatement.setObject(1, claass.getId(), Types.OTHER);
            preparedStatement.setString(2, claass.getName());
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Class added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Adding class failed.");
        }
    }

    @Override
    public void remove(Class claass) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "DELETE FROM classes WHERE id = ?;");
            preparedStatement.setObject(1, claass.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Class removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Removing class failed.");
        }
    }

    @Override
    public void edit(Class claass) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "UPDATE classes SET id = ?, name = ? WHERE id = ?;");
            preparedStatement.setObject(1, claass.getId(), Types.OTHER);
            preparedStatement.setString(2, claass.getName());
            preparedStatement.setObject(3, claass.getId());
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Class edited successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Editing class failed.");
        }
    }

    @Override
    public List<Class> getAll() {
        List<Class> classes = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM classes;");
            ResultSet classesSet = preparedStatement.executeQuery();
            while (classesSet.next()) {
                final UUID id = UUID.fromString(classesSet.getString("id"));
                final String name = classesSet.getString("name");
                Class claass = new Class(id, name);
                classes.add(claass);
            }
            dbConnection.disconnect();
            System.out.println("Selected classes from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting classes from data base failed.");
        }
        return classes;
    }

    @Override
    public Class get(UUID id) throws AbsenceOfRecordsException {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM classes WHERE id = ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet classesSet = preparedStatement.executeQuery();
            while (classesSet.next()) {
                final UUID questID = UUID.fromString(classesSet.getString("id"));
                final String name = classesSet.getString("name");
                Class claass = new Class(questID, name);
                return claass;
            }
            dbConnection.disconnect();
            System.out.println("Selected class from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting class from data base failed.");
        }
        throw new AbsenceOfRecordsException();
    }

    public List<Class> getAllStudentClasses(UUID id) {
        /*Getting all classes assigned to specific student by his id*/
        List<Class> classes = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    " SELECT id, name FROM classes, students_classes " +
                            "WHERE classes.id = students_classes.classes_id " +
                            "AND students_classes.student_id= ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet classesSet = preparedStatement.executeQuery();
            while (classesSet.next()) {
                final UUID classID = UUID.fromString(classesSet.getString("id"));
                final String name = classesSet.getString("name");
                Class claass = new Class(classID, name);
                classes.add(claass);
            }
            dbConnection.disconnect();
            System.out.println("Selected classes from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting classes from data base failed.");
        }
        return classes;
    }

    public List<Class> getAllMentorClasses(UUID id) {
        /*Getting all classes assigned to specific mentor by his id*/
        List<Class> classes = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    " SELECT id, name FROM classes, mentors_classes " +
                            "WHERE classes.id = mentors_classes.classes_id " +
                            "AND mentors_classes.mentor_id= ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet classesSet = preparedStatement.executeQuery();
            while (classesSet.next()) {
                final UUID classID = UUID.fromString(classesSet.getString("id"));
                final String name = classesSet.getString("name");
                Class claass = new Class(classID, name);
                classes.add(claass);
            }
            dbConnection.disconnect();
            System.out.println("Selected classes from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting classes from data base failed.");
        }
        return classes;
    }

}
