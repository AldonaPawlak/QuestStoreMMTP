package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.Artifact;

public class RemoveArtifactDAOImplementation implements RemoveDAO<Artifact> {

    DBConnection dbConnection;

    public RemoveArtifactDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(Artifact artifact) {
        dbConnection.ExecuteStatement(String.format("DELETE FROM artifacts WHERE id = %s;", artifact.getId()));
    }

}
