package org.example.DAO.EditDAO;

import org.example.DAO.DBConnection;
import org.example.model.Quest;

public class EditQuestsDAO implements EditDAO<Quest> {

    DBConnection dbConnection;

    public EditQuestsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(Quest quest) {
        dbConnection.ExecuteStatement(String.format("UPDATE quests SET name = '%s', description = '%s', value = %d WHERE id = '%s';", quest.getName(), quest.getDescription(), quest.getValue(), quest.getId()));
    }
}