package org.example.DAO;

import org.example.model.Class;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

public class ClassDAO implements DAO<Class>{

    DBConnection dbConnection;

    public ClassDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Class claass) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO classes (id, name) VALUES (?, ?);");
            preparedStatement.setObject(1, claass.getId(), Types.OTHER);
            preparedStatement.setString(2, claass.getName());
            preparedStatement.executeUpdate();
            dbConnection.disconnect();
            System.out.println("Class added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Adding class failed.");
        }
    }

    @Override
    public void remove(Class claass) {
        dbConnection.runSqlQuery(String.format("DELETE FROM classes WHERE id = '%s';", claass.getId()));
    }

    @Override
    public void edit(Class claass) {

    }

    @Override
    public List<Class> getAll() {
        return null;
    }

    @Override
    public Class get(UUID id) {
        return null;
    }

}
