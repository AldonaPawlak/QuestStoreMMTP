package org.example.DAO.AddDAO;

import org.example.DAO.DBConnection;
import org.example.model.SharedArtifactPayment;

public class AddSharedArtifactsPaymentsDAOImplementation implements AddDAO<SharedArtifactPayment> {


    DBConnection dbConnection;

    public AddSharedArtifactsPaymentsDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void add(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.ExecuteStatement(String.format("INSERT INTO shared_artifacts_payments(id, student_id, student_artifacts_id, payment) VALUES ('%s', '%s', '%s', '%d');", sharedArtifactPayment.getId(), sharedArtifactPayment.getStudentID(), sharedArtifactPayment.getStudentArtifactID(), sharedArtifactPayment.getPayment()));

    }
}
