package org.example.DAO.EditDAO;
import org.example.DAO.DBConnection;
import org.example.model.SharedArtifactPayment;

public class EditSharedArtifactsPaymentsDAO implements EditDAO<SharedArtifactPayment> {

    DBConnection dbConnection;

    public EditSharedArtifactsPaymentsDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void edit(SharedArtifactPayment sharedArtifactPayment) {
        dbConnection.ExecuteStatement(String.format("UPDATE shared_artifacts_payments SET payment = %d WHERE id = %s;", sharedArtifactPayment.getPayment(), sharedArtifactPayment.getId()));
    }
}