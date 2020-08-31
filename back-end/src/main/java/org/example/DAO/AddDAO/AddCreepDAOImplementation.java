package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.Creep;

import java.sql.Connection;

public class AddCreepDAOImplementation implements AddDAO<Creep>{

    DBConnection dbConnection;

    public AddCreepDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Creep creep) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO user_details(id, name, surname, email, password, role_id, is_active) VALUES (%s, %s ,%s ,%s, %s, 1, true);", creep.getId(), creep.getName(), creep.getSurname(), creep.getEmail(), creep.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO creep(id, user_details_id) VALUES (%s, %s)", creep.getId(), creep.getCreepID()));
    }
}
