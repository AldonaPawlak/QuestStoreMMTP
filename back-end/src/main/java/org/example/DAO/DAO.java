package org.example.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface DAO<T> {

    void add(T t) throws SQLException;
    void remove(T t) throws SQLException;
    void edit(T t);
    List<T> getAll();
    T get(UUID id) throws Exception;

}
