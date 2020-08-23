package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;

public class AddStudentDAOImplementation<Student> implements AddDAO<Student> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(Student student) {
         dbConnection.ExecuteStatement("INSERT into user_details (name, surname, email, password, role_id, is_active) values (%s, %s ,%s ,%s, %d, %b);", student.getName(); );
    }

}
