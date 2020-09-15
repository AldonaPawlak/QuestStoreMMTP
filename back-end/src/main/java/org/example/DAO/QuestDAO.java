package org.example.DAO;

import org.example.model.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        dbConnection.runSqlQuery(String.format("INSERT INTO quests (id, name, description, value) VALUES ('%s', '%s' ,'%s' ,'%d');", quest.getId(), quest.getName(), quest.getDescription(), quest.getValue()));
    }

    @Override
    public void remove(Quest quest) {
        dbConnection.runSqlQuery(String.format("REMOVE FROM quests WHERE id='%s';", quest.getId()));
    }

    @Override
    public void edit(Quest quest) {
        dbConnection.runSqlQuery(String.format("UPDATE quests SET name = '%s', description = '%s', value = %d WHERE id = '%s';", quest.getName(), quest.getDescription(), quest.getValue(), quest.getId()));
    }

    @Override
    public List<Quest> getAll()  {
        List<Quest> quests = new ArrayList<>();
        try {
            ResultSet questsSet = daoGetSet.getDataSet("SELECT * FROM quests;");
            while (questsSet.next()) {
                final UUID id = UUID.fromString(questsSet.getString("id"));
                final String name = questsSet.getString("name");
                final String description = questsSet.getString("description");
                final int value = questsSet.getInt("value");
                Quest quest = new Quest(id, name, description, value);
                quests.add(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Quest get(UUID id) {
        List<Quest> quests = new ArrayList<>();
        try {
            ResultSet questsSet = daoGetSet.getDataSet("SELECT * FROM quests;");
            while (questsSet.next()) {
                final UUID questID = UUID.fromString(questsSet.getString("id"));
                final String name = questsSet.getString("name");
                final String description = questsSet.getString("description");
                final int value = questsSet.getInt("value");
                Quest quest = new Quest(questID, name, description, value);
                quests.add(quest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests.get(0);
    }

}
