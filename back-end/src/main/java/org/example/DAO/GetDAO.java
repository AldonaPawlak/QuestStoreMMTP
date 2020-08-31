package org.example.DAO;

import org.example.model.Student;

import java.sql.ResultSet;
import java.util.List;

public interface GetDAO {

    ResultSet getDataSet(String query);
    public List<Student> getAllEntries(String query);

}
