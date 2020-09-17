package org.example.DAO;

import org.example.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO user_details (id, name, surname, email, password, role_id, is_active, phone_number)" +
                            " VALUES (?, ?, ?, ?, ?, ?, true, ?);");
            preparedStatement.setObject(1, student.getUserDetailsID(), Types.OTHER);
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getPassword());
            preparedStatement.setObject(6, student.getRoleID(), Types.OTHER);
            preparedStatement.setString(7, student.getPhoneNumber());
            preparedStatement.executeUpdate();
            System.out.println("Added user successfully.");
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO students (student_id, user_details_id, coins) VALUES (?, ?, 0);");
            statement.setObject(1, student.getStudentID(), Types.OTHER);
            statement.setObject(2, student.getUserDetailsID());
            statement.executeUpdate();
            System.out.println("Added student successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Adding student failed.");
        }
    }

    @Override
    public void remove(Student student) {
        dbConnection.runSqlQuery(String.format("DELETE FROM students WHERE id ='%s';", student.getStudentID()));
        dbConnection.runSqlQuery(String.format("DELETE FROM user_details WHERE id='%s';", student.getUserDetailsID()));
    }

    @Override
    public void edit(Student student) {
        dbConnection.runSqlQuery(String.format("UPDATE students SET coins = %d WHERE id = '%s';", student.getCoins(), student.getStudentID()));
        dbConnection.runSqlQuery(String.format("UPDATE user_details SET name = '%s', surname = '%s', email = '%s', password = '%s', role_id = '%s', is_active = '%b' WHERE id = '%s;'", student.getName(), student.getSurname(), student.getEmail(), student.getPassword(), student.getRoleID(), student.isActive(), student.getUserDetailsID()));
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
