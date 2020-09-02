package org.example.DAO;

import org.example.model.Creep;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CreepDAOImplementation implements  DAO<Creep> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public CreepDAOImplementation(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Creep creep) {
        dbConnection.executeStatement(String.format("INSERT INTO user_details(id, name, surname, email, password, role_id, is_active) VALUES ('%s', '%s' ,'%s' ,'%s', '%s', 1, true);", creep.getUserDetailsID(), creep.getName(), creep.getSurname(), creep.getEmail(), creep.getPassword()));
        dbConnection.executeStatement(String.format("INSERT INTO creep(id, user_details_id) VALUES ('%s', '%s')", creep.getCreepID(), creep.getUserDetailsID()));
    }

    @Override
    public void remove(Creep creep) {
        dbConnection.executeStatement(String.format("DELETE FROM creep WHERE id = '%s';", creep.getUserDetailsID()));
        dbConnection.executeStatement(String.format("DELETE FROM user_details WHERE id = '%s';", creep.getUserDetailsID()));
    }

    @Override
    public void edit(Creep creep) {
        dbConnection.executeStatement(String.format("UPDATE creeps SET user_details_id = '%s' WHERE id = '%s';", creep.getUserDetailsID(), creep.getCreepID()));
        dbConnection.executeStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", creep.getName(), creep.getSurname(), creep.getEmail(), creep.getPassword(), creep.getRoleID(), creep.isActive(), creep.getUserDetailsID()));
    }

    @Override
    public List<Creep> getAll() throws SQLException {
        return null;
    }

    @Override
    public Creep get(UUID id) {
        return null;
    }

}
