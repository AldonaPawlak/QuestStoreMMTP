package org.example.DAO;

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
    DAOGetSet daoGetSet;

    public QuestDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO (id, name, description, value) VALUES (?, ?, ?, ?);");
            preparedStatement.setObject(1, quest.getId(), Types.OTHER);
            preparedStatement.setString(2, quest.getName());
            preparedStatement.setString(3, quest.getDescription());
            preparedStatement.setInt(4, quest.getValue());
            preparedStatement.executeUpdate();
            System.out.println("Quest added successfully.");
            dbConnection.disconnect();
        } catch (Exception e) {
            System.out.println("Adding quest failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("REMOVE FROM quests WHERE id = ?;");
            preparedStatement.setObject(1, quest.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Quest removed successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            System.out.println("Removing quest failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Quest quest) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE quests SET name = ?, description = ?, value = ? WHERE id = ?;");
            preparedStatement.setString(1, quest.getName());
            preparedStatement.setString(2, quest.getDescription());
            preparedStatement.setInt(3, quest.getValue());
            preparedStatement.setObject(4, quest.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Quest edited successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            System.out.println("Editing quest failed.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Quest> getAll()  {
        List<Quest> quests = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement("SELECT * FROM quests;");
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
            System.out.println("Selecting quests from data base failed.");
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Quest get(UUID id) {
        List<Quest> quests = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement("SELECT * FROM quests WHERE id = ?;");
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
            System.out.println("Selected quest from data base successfully.");
        } catch (SQLException e) {
            System.out.println("Selecting quest from data base failed.");
            e.printStackTrace();
        }
        return quests.get(0);
    }

}
