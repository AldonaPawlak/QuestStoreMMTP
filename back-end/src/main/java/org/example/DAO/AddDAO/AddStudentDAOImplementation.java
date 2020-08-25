package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;

public class AddStudentDAOImplementation<Student> implements AddDAO<org.example.model.Student> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(org.example.model.Student student) {
         dbConnection.ExecuteStatement(String.format("INSERT into user_details (name, surname, email, password, role_id, is_active)" + "values (%s, %s ,%s ,%s, %d, %b);", student.getName()));
    }

}
