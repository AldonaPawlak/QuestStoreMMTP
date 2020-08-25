package org.example.controller;

import org.example.DAO.AddDAO.AddDAO;
import org.example.DAO.DBConnection;

public class AddClassDaoImplementation implements AddDAO<org.example.model.Class> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(org.example.model.Class cclass) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO classes (id, name) VALUES (%s, %s);", cclass.getId(), cclass.getName()));
    }

}
