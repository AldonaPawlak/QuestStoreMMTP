package org.example.DAO.RemoveDAO;

import org.example.DAO.DBConnection;
import org.example.model.SharedArtifactPayment;

public class RemoveSharedArtifactsPaymentsDAOImplementation implements RemoveDAO<SharedArtifactPayment> {

    DBConnection dbConnection;

    public RemoveSharedArtifactsPaymentsDAOImplementation(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void remove(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.ExecuteStatement(String.format("REMOVE FROM shared_artifacts_payments '%s';", sharedArtifactPayment.getId()));
    }
}
