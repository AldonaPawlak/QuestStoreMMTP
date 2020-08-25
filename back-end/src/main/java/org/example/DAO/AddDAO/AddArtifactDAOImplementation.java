package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;

public class AddArtifactDAOImplementation<Artifact> implements AddDAO<org.example.model.Artifact> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public void add(org.example.model.Artifact artifact) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO artifacts (id, name, price, category_id, description, artifact_type_id)" +  "VALUES();",));
    }

}
