package org.example.DAO;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Quest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestDAO implements DAO<Quest> {

    DBConnection dbConnection;

    public QuestDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO (id, name, description, value) VALUES (?, ?, ?, ?);");
            preparedStatement.setObject(1, quest.getId(), Types.OTHER);
            preparedStatement.setString(2, quest.getName());
            preparedStatement.setString(3, quest.getDescription());
            preparedStatement.setInt(4, quest.getValue());
            preparedStatement.executeUpdate();
            System.out.println("Quest added successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Adding quest failed.");
        }
    }

    @Override
    public void remove(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "DELETE FROM quests WHERE id = ?;");
            preparedStatement.setObject(1, quest.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Quest removed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Removing quest failed.");
        }
    }

    @Override
    public void edit(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "UPDATE quests SET name = ?, description = ?, value = ? WHERE id = ?;");
            preparedStatement.setString(1, quest.getName());
            preparedStatement.setString(2, quest.getDescription());
            preparedStatement.setInt(3, quest.getValue());
            preparedStatement.setObject(4, quest.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Quest edited successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Editing quest failed.");
        }
    }

    @Override
    public List<Quest> getAll()  {
        List<Quest> quests = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM quests ORDER BY name;");
            ResultSet questsSet = preparedStatement.executeQuery();
            while (questsSet.next()) {
                final UUID id = UUID.fromString(questsSet.getString("id"));
                final String name = questsSet.getString("name");
                final String description = questsSet.getString("description");
                final int value = questsSet.getInt("value");
                Quest quest = new Quest(id, name, description, value);
                quests.add(quest);
            }
            dbConnection.disconnect();
            System.out.println("Selected quests from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting quests from data base failed.");
        }
        return quests;
    }

    @Override
    public Quest get(UUID id) throws AbsenceOfRecordsException {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM quests WHERE id = ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet questsSet = preparedStatement.executeQuery();
            while (questsSet.next()) {
                final UUID questID = UUID.fromString(questsSet.getString("id"));
                final String name = questsSet.getString("name");
                final String description = questsSet.getString("description");
                final int value = questsSet.getInt("value");
                Quest quest = new Quest(questID, name, description, value);
                return quest;
            }
            dbConnection.disconnect();
            System.out.println("Selected quest from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting quest from data base failed.");
        }
        throw new AbsenceOfRecordsException();
    }

    public List<Quest> getAllStudentQuests(UUID id)  {
        /*We have to pass here student id*/
        List<Quest> quests = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT name, description, value FROM quests, students " +
                            "WHERE students.user_details_id = ? ORDER BY name;;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet questsSet = preparedStatement.executeQuery();
            while (questsSet.next()) {
                final UUID questID = UUID.fromString(questsSet.getString("id"));
                final String name = questsSet.getString("name");
                final String description = questsSet.getString("description");
                final int value = questsSet.getInt("value");
                Quest quest = new Quest(questID, name, description, value);
                quests.add(quest);
            }
            dbConnection.disconnect();
            System.out.println("Selected students quests from data base successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Selecting students quests from data base failed.");
        }
        return quests;
    }

}
