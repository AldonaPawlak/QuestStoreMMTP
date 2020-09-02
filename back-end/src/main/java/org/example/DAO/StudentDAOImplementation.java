package org.example.DAO;

import org.example.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDAOImplementation implements DAO<Student> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public StudentDAOImplementation(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }


    @Override
    public void add(Student student) {
        dbConnection.ExecuteStatement(String.format("'INSERT INTO user_details (id, name, surname, email, password, role_id, is_active) VALUES ('%s', '%s' ,'%s' ,'%s', '%s', 3, true);'", student.getUserDetailsID(), student.getName(), student.getSurname(), student.getEmail(), student.getPassword()));
        dbConnection.ExecuteStatement(String.format("INSERT INTO students (student_id, user_details_id, coins) VALUES ('%s', '%s', 0);", student.getStudentID(), student.getUserDetailsID()));
    }

    @Override
    public void remove(Student student) {
        dbConnection.ExecuteStatement(String.format("REMOVE FROM students '%s';", student.getStudentID()));
        dbConnection.ExecuteStatement(String.format("REMOVE FROM user_details '%s';", student.getUserDetailsID()));
    }

    @Override
    public void edit(Student student) {
        dbConnection.ExecuteStatement(String.format("UPDATE students SET coins = %d WHERE id = '%s';", student.getCoins(), student.getStudentID()));
        dbConnection.ExecuteStatement(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", student.getName(), student.getSurname(), student.getEmail(), student.getPassword(), student.getRoleID(), student.isActive(), student.getUserDetailsID()));
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
                Student student = new Student(userDetailsID, name, surname, email, password, roleID, studentID, coins, isActive);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student get(UUID id) throws SQLException {
        ResultSet result = daoGetSet.getDataSet(String.format("SELECT * FROM user_details, students WHERE user_details.id = students.user_details_id AND id='%s';", id));
        final UUID userDetailsID = id;
        final String name = result.getString("name");
        final String surname = result.getString("surname");
        final String email = result.getString("email");
        final String password = result.getString("password");
        final UUID roleID = UUID.fromString(result.getString("role_id"));
        final UUID studentID = UUID.fromString(result.getString("student_id"));
        final int coins = result.getInt("coins");
        final boolean isActive = result.getBoolean("is_active");
        Student student = new Student(userDetailsID, name, surname, email, password, roleID, studentID, coins, isActive);
        return student;
    }

}
