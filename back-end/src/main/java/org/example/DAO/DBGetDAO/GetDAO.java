package org.example.DAO.DBGetDAO;

import java.sql.ResultSet;
import java.util.List;

public interface GetDAO {

    ResultSet getDataSet(String query);
    List<?> getAll(String query);
    List<?> getAll(String query, String secondQuery);

}
