package org.example.DAO.EditDAO;

import org.example.DAO.DBConnection;
import org.example.model.Artifact;

public class EditArtifactDAO implements EditDAO<Artifact> {

    DBConnection dbConnection;

    public EditArtifactDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(Artifact artifact) {
        dbConnection.ExecuteStatement(String.format("UPDATE artifacts SET name = '%s', price = %d, category_id = '%s', description = '%s', artifact_type_id = '%s' WHERE id = '%s';", artifact.getName(), artifact.getPrice(), artifact.getCategoryID(), artifact.getDescription(), artifact.getArtifactTypeID(), artifact.getId()));
    }
}