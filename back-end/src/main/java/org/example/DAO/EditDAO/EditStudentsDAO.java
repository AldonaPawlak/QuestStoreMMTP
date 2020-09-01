package org.example.DAO.EditDAO;
import org.example.DAO.DBConnection;
import org.example.model.Student;

public class EditStudentsDAO implements EditDAO<Student> {

    DBConnection dbConnection;

    public EditStudentsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(Student student) {
        dbConnection.ExecuteStatement(String.format("UPDATE students SET coins = %d WHERE id = '%s';", student.getCoins(), student.getStudentID()));
    }
}