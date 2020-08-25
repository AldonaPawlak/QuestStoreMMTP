package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.Artifact;

public class AddArtifactDAOImplementation implements AddDAO<Artifact> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(Artifact artifact) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO artifacts (id, name, price, category_id, description, artifact_type_id) VALUES(%s, %s, %d, %s, %s, %s);", artifact.getId(), artifact.getName(), artifact.getCategoryID(), artifact.getDescription(), artifact.getArtifactTypeID()));
    }

}
