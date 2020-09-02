package org.example.DAO;

import org.example.model.Mentor;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MentorDAOImplementation implements DAO<Mentor> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public MentorDAOImplementation(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES ('%s', '%s' ,'%s' ,'%s', '%s', 2, true);", mentor.getUserDetailsID(), mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO mentors (id, user_details_id) VALUES ('%s', '%s');", mentor.getMentorID(), mentor.getUserDetailsID()));
    }

    @Override
    public void remove(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM mentors '%s';", mentor.getMentorID()));
        dbConnection.ExecuteStatement(String.format("DELETE FROM user_details WHERE id = '%s';", mentor.getUserDetailsID()));
    }

    @Override
    public void edit(Mentor mentor) {
        dbConnection.ExecuteStatement(String.format("UPDATE mentors SET user_details_id = '%s' WHERE id = '%s';", mentor.getUserDetailsID(), mentor.getMentorID()));
        dbConnection.ExecuteStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", mentor.getName(), mentor.getSurname(), mentor.getEmail(), mentor.getPassword(), mentor.getRoleID(), mentor.isActive(), mentor.getUserDetailsID()));
    }

    @Override
    public List<Mentor> getAll() throws SQLException {
        return null;
    }

    @Override
    public Mentor get(UUID id) {
        return null;
    }

}
