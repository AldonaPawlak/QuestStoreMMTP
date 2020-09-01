package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Student;

public class RemoveStudentDAOImplementation implements RemoveDAO<Student> {

    DBConnection dbConnection;

    public RemoveStudentDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    @Override
    public void remove(Student student) {
        dbConnection.ExecuteStatement(String.format("REMOVE FROM user_details %s;", student.getUserDetailsID()));
        dbConnection.ExecuteStatement(String.format("REMOVE FROM students %s;", student.getStudentID()));
    }
}
