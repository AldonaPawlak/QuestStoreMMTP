package org.example.DAO.EditDAO;

import org.example.DAO.DBConnection;
import org.example.model.Creep;

public class EditCreepsDAO implements EditDAO<Creep> {

    DBConnection dbConnection;

    public EditCreepsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(Creep creep) {
        dbConnection.ExecuteStatement(String.format("UPDATE creeps SET user_details_id = %s WHERE id = %s;", creep.getId(), creep.getCreepID()));
    }
}