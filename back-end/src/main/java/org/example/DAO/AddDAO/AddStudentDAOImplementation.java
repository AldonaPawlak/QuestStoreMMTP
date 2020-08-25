package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;

public class AddStudentDAOImplementation<Student> implements AddDAO<org.example.model.Student> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(org.example.model.Student student) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES (%s, %s ,%s ,%s, %s, 3, true);", student.getId(), student.getName(), student.getSurname(), student.getEmail(), student.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO students VALUES(id, user_details_id, coins) VALUES(%s, %s, 0);", student.getStudentID(), student.getUserDetailsID()));
    }

}
