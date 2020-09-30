package org.example.DAO;

import net.bytebuddy.agent.VirtualMachine;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginDAOTest {

    MentorDAO mentorDAO;
    DBConnection dbConnection;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    @BeforeEach
    void init(){
        mentorDAO = mock(MentorDAO.class);
        dbConnection = mock(DBConnection.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

    }

    @Test
    void login() throws Exception {
//        doNothing().when(dbConnection).connect();
        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("id")).thenReturn("11111111-1111-1111-1111-111111111111");
        when(resultSet.getString("role")).thenReturn("mentor");
        when(mentorDAO.get(UUID.fromString("11111111-1111-1111-1111-111111111111"))).thenReturn(null);

        LoginDAO loginDAO = new LoginDAO(dbConnection, mentorDAO, null, null);

        loginDAO.login("ala@ala.pl", "123");

        // sprawdzenie czy preparestatement zostalo sparametryzowane emeilem i hasłem (tylko i wyłącznie)

        // sprawdzic czy mentorDao.get zostalo wywołane z odpowiednim parametrem
        // czy dbConnection.connect() zostało wywołane




    }
}