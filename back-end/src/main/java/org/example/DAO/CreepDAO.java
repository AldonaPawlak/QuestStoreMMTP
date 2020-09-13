package org.example.DAO;

import org.example.model.Creep;
import org.example.model.Mentor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreepDAO implements  DAO<Creep> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public CreepDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
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
    public List<Creep> getAll() {
        List<Creep> mentors = new ArrayList<>();
        try {
            ResultSet allMentors = daoGetSet.getDataSet("SELECT * FROM user_details, creeps WHERE user_details.id = creeps.user_details_id;");
            while (allMentors.next()) {
                final UUID userDetailsID = UUID.fromString(allMentors.getString("id"));
                final String name = allMentors.getString("name");
                final String surname = allMentors.getString("surname");
                final String email = allMentors.getString("email");
                final String password = allMentors.getString("password");
                final UUID roleID = UUID.fromString(allMentors.getString("role_id"));
                final UUID creepID = UUID.fromString(allMentors.getString("creep_id"));
                final boolean isActive = allMentors.getBoolean("is_active");
                final String phoneNumber = allMentors.getString("phone_number");
                Creep creep = new Creep(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, creepID);
                mentors.add(creep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }

    @Override
    public Creep get(UUID id) throws  SQLException{
        ResultSet result = daoGetSet.getDataSet(String.format("SELECT * FROM user_details, mentors WHERE user_details.id = mentors.user_details_id AND id='%s';", id));
        final UUID userDetailsID = id;
        final String name = result.getString("name");
        final String surname = result.getString("surname");
        final String email = result.getString("email");
        final String password = result.getString("password");
        final UUID roleID = UUID.fromString(result.getString("role_id"));
        final UUID creepID = UUID.fromString(result.getString("student_id"));
        final boolean isActive = result.getBoolean("is_active");
        final String phoneNumber = result.getString("phone_number");
        Creep creep = new Creep(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, creepID);
        return creep;
    }

}
