package org.example;

import org.example.DAO.AddDAO.AddStudentDAOImplementation;
import org.example.DAO.DBConnection;
import org.example.model.Student;

public class App
{
    public static void main( String[] args )
    {
        AddStudentDAOImplementation a = new AddStudentDAOImplementation();
        Student student = new Student();
        a.add();
    }
}
