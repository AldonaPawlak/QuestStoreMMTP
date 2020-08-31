package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Creep;

public class RemoveCreepDAOImplementation implements RemoveDAO<Creep> {

    DBConnection dbConnection;

    public RemoveCreepDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(Creep creep) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM user_details WHERE id = %s;", creep.getId()));
        dbConnection.ExecuteStatement(String.format("DELETE FROM creep WHERE id = %s", creep.getId()));
    }
}
