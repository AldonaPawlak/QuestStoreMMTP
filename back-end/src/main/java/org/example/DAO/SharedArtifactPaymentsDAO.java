package org.example.DAO;

import org.example.model.SharedArtifactPayment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SharedArtifactPaymentsDAO implements DAO<SharedArtifactPayment> {

    DBConnection dbConnection;
    DAOGetSet daoGetSet;

    public SharedArtifactPaymentsDAO(DBConnection dbConnection, DAOGetSet daoGetSet) {
        this.dbConnection = dbConnection;
        this.daoGetSet = daoGetSet;
    }

    @Override
    public void add(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.executeStatement(String.format("INSERT INTO shared_artifacts_payments(id, student_id, student_artifacts_id, payment) VALUES ('%s', '%s', '%s', '%d');", sharedArtifactPayment.getId(), sharedArtifactPayment.getStudentID(), sharedArtifactPayment.getStudentArtifactID(), sharedArtifactPayment.getPayment()));
    }

    @Override
    public void remove(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.executeStatement(String.format("REMOVE FROM shared_artifacts_payments '%s';", sharedArtifactPayment.getId()));
    }

    @Override
    public void edit(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.executeStatement(String.format("UPDATE shared_artifacts_payments SET payment = %d WHERE id = '%s';", sharedArtifactPayment.getPayment(), sharedArtifactPayment.getId()));
    }

    @Override
    public List<SharedArtifactPayment> getAll() {
        List<SharedArtifactPayment> sharedArtifactPayments = new ArrayList<>();
        try {
            ResultSet allArtifactPayments = daoGetSet.getDataSet("SELECT * FROM shared_artifacts_payments;");
            while (allArtifactPayments.next()) {
                final UUID id = UUID.fromString(allArtifactPayments.getString("id"));
                final UUID studentID = UUID.fromString(allArtifactPayments.getString("student_id"));
                final UUID studentArtifactID = UUID.fromString(allArtifactPayments.getString("student_artifact_id"));
                final int payment = allArtifactPayments.getInt("payment");
                SharedArtifactPayment sharedArtifactPayment = new SharedArtifactPayment(id, studentID, studentArtifactID, payment);
                sharedArtifactPayments.add(sharedArtifactPayment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sharedArtifactPayments;
    }

    @Override
    public SharedArtifactPayment get(UUID id) {
        List<SharedArtifactPayment> sharedArtifactPayments = new ArrayList<>();
        try {
            ResultSet allArtifactPayments = daoGetSet.getDataSet(String.format("SELECT * FROM shared_artifacts_payments WHERE id ='%s';", id));
            while (allArtifactPayments.next()) {
                final UUID artifactID = UUID.fromString(allArtifactPayments.getString("id"));
                final UUID studentID = UUID.fromString(allArtifactPayments.getString("student_id"));
                final UUID studentArtifactID = UUID.fromString(allArtifactPayments.getString("student_artifact_id"));
                final int payment = allArtifactPayments.getInt("payment");
                SharedArtifactPayment sharedArtifactPayment = new SharedArtifactPayment(artifactID, studentID, studentArtifactID, payment);
                sharedArtifactPayments.add(sharedArtifactPayment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sharedArtifactPayments.get(0);
    }

}
