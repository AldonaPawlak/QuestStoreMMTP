package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.Quest;

public class AddQuestDAOImplementation implements AddDAO<Quest> {
    DBConnection dbConnection;

    public AddQuestDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Quest quest) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO quests (id, name, description, value) VALUES ('%s', '%s' ,'%s' ,'%d');", quest.getId(), quest.getName(), quest.getDescription(), quest.getValue()));
    }


}
