package org.example.DAO;

import org.example.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDAO implements DAO<Student> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public StudentDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }


    @Override
    public void add(Student student) {
        dbConnection.executeStatement(String.format("'INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES ('%s', '%s' ,'%s' ,'%s', '%s', 3, true);'", student.getUserDetailsID(), student.getName(), student.getSurname(), student.getEmail(), student.getPassword()));
        dbConnection.executeStatement(String.format("INSERT INTO students (student_id, user_details_id, coins) VALUES ('%s', '%s', 0);", student.getStudentID(), student.getUserDetailsID()));
    }

    @Override
    public void remove(Student student) {
        dbConnection.executeStatement(String.format("REMOVE FROM students WHERE id ='%s';", student.getStudentID()));
        dbConnection.executeStatement(String.format("REMOVE FROM user_details WHERE id='%s';", student.getUserDetailsID()));
    }

    @Override
    public void edit(Student student) {
        dbConnection.executeStatement(String.format("UPDATE students SET coins = %d WHERE id = '%s';", student.getCoins(), student.getStudentID()));
        dbConnection.executeStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", student.getName(), student.getSurname(), student.getEmail(), student.getPassword(), student.getRoleID(), student.isActive(), student.getUserDetailsID()));
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            ResultSet allStudents = daoGetSet.getDataSet("SELECT * FROM user_details, students WHERE user_details.id = students.user_details_id;");
            while (allStudents.next()) {
                final UUID userDetailsID = UUID.fromString(allStudents.getString("id"));
                final String name = allStudents.getString("name");
                final String surname = allStudents.getString("surname");
                final String email = allStudents.getString("email");
                final String password = allStudents.getString("password");
                final UUID roleID = UUID.fromString(allStudents.getString("role_id"));
                final UUID studentID = UUID.fromString(allStudents.getString("student_id"));
                final int coins = allStudents.getInt("coins");
                final boolean isActive = allStudents.getBoolean("is_active");
                final String phoneNumber = allStudents.getString("phone_number");
                Student student = new Student(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, studentID, coins);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student get(UUID id) {
        List<Student> students = new ArrayList<>();
        try {
            ResultSet allStudents = daoGetSet.getDataSet(String.format("SELECT * FROM user_details, students WHERE user_details.id = students.user_details_id AND id ='%s';", id));
            while (allStudents.next()) {
                final UUID userDetailsID = UUID.fromString(allStudents.getString("id"));
                final String name = allStudents.getString("name");
                final String surname = allStudents.getString("surname");
                final String email = allStudents.getString("email");
                final String password = allStudents.getString("password");
                final UUID roleID = UUID.fromString(allStudents.getString("role_id"));
                final UUID studentID = UUID.fromString(allStudents.getString("student_id"));
                final int coins = allStudents.getInt("coins");
                final boolean isActive = allStudents.getBoolean("is_active");
                final String phoneNumber = allStudents.getString("phone_number");
                Student student = new Student(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, studentID, coins);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students.get(0);
    }

}
