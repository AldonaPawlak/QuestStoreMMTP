package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Class;

public class RemoveClassDaoImplementation implements RemoveDAO<Class> {

    DBConnection dbConnection;

    public RemoveClassDaoImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(Class cclass) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM classes WHERE id = %s;", cclass.getId()));
    }

}
