package org.example.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface DAO<T> {

    void add(T t);
    void remove(T t);
    void edit(T t);
    List<T> getAll() throws SQLException;
    T get(UUID id);

}
