package org.example.DAO.EditDAO;
import org.example.DAO.DBConnection;
import org.example.model.User;

public class EditUserDetailsDAO implements EditDAO<User> {

    DBConnection dbConnection;

    public EditUserDetailsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(User user) {
        dbConnection.ExecuteStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRoleID(), user.isActive(), user.getId()));
    }
}