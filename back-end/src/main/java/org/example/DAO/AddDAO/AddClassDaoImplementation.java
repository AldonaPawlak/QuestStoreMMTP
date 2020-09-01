package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.Class;

public class
AddClassDaoImplementation implements AddDAO<Class> {

    DBConnection dbConnection;

    public AddClassDaoImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Class cclass) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO classes (id, name) VALUES ('%s', '%s');", cclass.getId(), cclass.getName()));
    }

}
