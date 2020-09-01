package org.example.DAO.EditDAO;
import org.example.DAO.DBConnection;
import org.example.model.StudentArtifact;

public class EditStudentArtifactsDAO implements EditDAO<StudentArtifact> {

    DBConnection dbConnection;

    public EditStudentArtifactsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(StudentArtifact studentArtifact) {
        dbConnection.ExecuteStatement(String.format("UPDATE student_artifacts SET status = % WHERE id = %s;", studentArtifact.isStatus(), studentArtifact.getId()));
    }
}