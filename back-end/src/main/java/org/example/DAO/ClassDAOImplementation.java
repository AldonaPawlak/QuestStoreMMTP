package org.example.DAO;

import org.example.model.Class;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ClassDAOImplementation implements DAO<Class>{

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public ClassDAOImplementation(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Class claass) {
        dbConnection.executeStatement(String.format("INSERT INTO classes (id, name) VALUES ('%s', '%s');", claass.getId(), claass.getName()));
    }

    @Override
    public void remove(Class claass) {
        dbConnection.executeStatement(String.format("DELETE FROM classes WHERE id = '%s';", claass.getId()));
    }

    @Override
    public void edit(Class claass) {

    }

    @Override
    public List<Class> getAll() throws SQLException {
        return null;
    }

    @Override
    public Class get(UUID id) {
        return null;
    }

}
