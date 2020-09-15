package org.example.DAO;

import org.example.model.Artifact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class ArtifactDAO implements DAO<Artifact>{

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public ArtifactDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(Artifact artifact) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement("INSERT INTO artifacts (id, name, price, category_id, description, artifact_type_id) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setObject(1, artifact.getId(), Types.OTHER);
            preparedStatement.setString(2, artifact.getName());
            preparedStatement.setInt(3, artifact.getPrice());
            preparedStatement.setObject(4,artifact.getCategoryID(), Types.OTHER);
            preparedStatement.setString(5, artifact.getDescription());
            preparedStatement.setObject(6, artifact.getArtifactTypeID(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Artifact added successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            System.out.println("Adding artifact failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Artifact artifact) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("DELETE FROM artifacts WHERE id = ?;");
            preparedStatement.setObject(1, artifact.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Artifact removed successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            System.out.println("Removing artifact failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Artifact artifact) {
        dbConnection.runSqlQuery(String.format("UPDATE artifacts SET name = '%s', price = %d, category_id = '%s', description = '%s', artifact_type_id = '%s' WHERE id = '%s';", artifact.getName(), artifact.getPrice(), artifact.getCategoryID(), artifact.getDescription(), artifact.getArtifactTypeID(), artifact.getId()));
    }

    @Override
    public List<Artifact> getAll() {
        List<Artifact> artifacts = new ArrayList<>();
        try {
            ResultSet allArtifacts = daoGetSet.getDataSet("SELECT * FROM artifacts;");
            while (allArtifacts.next()) {
                final UUID id = UUID.fromString(allArtifacts.getString("id"));
                final String name = allArtifacts.getString("name");
                final int price = allArtifacts.getInt("price");
                final UUID categoryID = UUID.fromString(allArtifacts.getString("category_id"));
                final String description = allArtifacts.getString("description");
                final UUID artifactTypeID = UUID.fromString(allArtifacts.getString("artifact_type_id"));
                Artifact artifact = new Artifact(id, name, price, categoryID, description, artifactTypeID);
                artifacts.add(artifact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts;
    }

    @Override
    public Artifact get(UUID id) {
        List<Artifact> artifacts = new ArrayList<>();
        try {
            ResultSet allArtifacts = daoGetSet.getDataSet("SELECT * FROM artifacts;");
            while (allArtifacts.next()) {
                final UUID artifactID = UUID.fromString(allArtifacts.getString("id"));
                final String name = allArtifacts.getString("name");
                final int price = allArtifacts.getInt("price");
                final UUID categoryID = UUID.fromString(allArtifacts.getString("category_id"));
                final String description = allArtifacts.getString("description");
                final UUID artifactTypeID = UUID.fromString(allArtifacts.getString("artifact_type_id"));
                Artifact artifact = new Artifact(artifactID, name, price, categoryID, description, artifactTypeID);
                artifacts.add(artifact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artifacts.get(0);
    }

}
