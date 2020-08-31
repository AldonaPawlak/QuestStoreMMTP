package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Quest;

public class RemoveQuestDAOImplementation implements RemoveDAO<Quest> {
    DBConnection dbConnection;

    public RemoveQuestDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(Quest quest) {
        dbConnection.ExecuteStatement(String.format("REMOVE FROM quests %s;", quest.getId()));
    }


}
