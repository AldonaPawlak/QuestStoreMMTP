package org.example.DAO;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.model.Artifact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class ArtifactDAO implements DAO<Artifact>{

    DBConnection dbConnection;

    public ArtifactDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(Artifact artifact) {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "INSERT INTO artifacts (id, name, price, category_id, description, artifact_type_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?);");
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
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "DELETE FROM artifacts WHERE id = ?;");
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
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(
                    "UPDATE artifacts SET name = ?, price = ?, description = ? WHERE id = ?;");
            preparedStatement.setString(1, artifact.getName());
            preparedStatement.setInt(2, artifact.getPrice());
            preparedStatement.setString(3, artifact.getDescription());
            preparedStatement.setObject(4, artifact.getId(), Types.OTHER);
            preparedStatement.executeUpdate();
            System.out.println("Artifact edited successfully.");
            dbConnection.disconnect();
        } catch (SQLException e) {
            System.out.println("Editing artifact failed.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Artifact> getAll() {
        List<Artifact> artifacts = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM artifacts;");
            ResultSet allArtifacts = preparedStatement.executeQuery();
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
            dbConnection.disconnect();
            System.out.println("Selected artifacts from data base successfully.");
        } catch (SQLException e) {
            System.out.println("Selecting artifacts from data base failed.");
            e.printStackTrace();
        }
        return artifacts;
    }

    @Override
    public Artifact get(UUID id) throws AbsenceOfRecordsException {
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT * FROM artifacts WHERE id = ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet allArtifacts = preparedStatement.executeQuery();
            while (allArtifacts.next()) {
                final UUID artifactID = UUID.fromString(allArtifacts.getString("id"));
                final String name = allArtifacts.getString("name");
                final int price = allArtifacts.getInt("price");
                final UUID categoryID = UUID.fromString(allArtifacts.getString("category_id"));
                final String description = allArtifacts.getString("description");
                final UUID artifactTypeID = UUID.fromString(allArtifacts.getString("artifact_type_id"));
                Artifact artifact = new Artifact(artifactID, name, price, categoryID, description, artifactTypeID);
                return artifact;
            }
            dbConnection.disconnect();
            System.out.println("Selected artifact from data base successfully.");
        } catch (SQLException e) {
            System.out.println("Selecting artifact from data base failed.");
            e.printStackTrace();
        }
        throw new AbsenceOfRecordsException();
    }

    public List<Artifact> getAllStudentArtifacts(UUID id) {
        /*selecting all artifacts belongings to student by his id*/
        List<Artifact> artifacts = new ArrayList<>();
        try {
            dbConnection.connect();
            PreparedStatement preparedStatement = dbConnection.connect().prepareStatement(
                    "SELECT artifacts.id, name, price, category_id, description, status, artifact_type_id " +
                            "FROM artifacts, student_artifacts WHERE artifacts.id = student_artifacts.artifact_id " +
                            "AND student_artifacts.student_id = ?;");
            preparedStatement.setObject(1, id, Types.OTHER);
            ResultSet allArtifacts = preparedStatement.executeQuery();
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
            dbConnection.disconnect();
            System.out.println("Selected artifacts from data base successfully.");
        } catch (SQLException e) {
            System.out.println("Selecting artifacts from data base failed.");
            e.printStackTrace();
        }
        return artifacts;
    }

}

