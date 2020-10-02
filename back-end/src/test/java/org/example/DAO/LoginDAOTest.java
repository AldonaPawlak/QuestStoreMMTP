package org.example.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
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
    void loginTest() throws Exception {
        String email = "ala@ala.pl";
        String password = "pass";
        String uuidString = "11111111-1111-1111-1111-111111111111";
        UUID uuid = UUID.fromString("11111111-1111-1111-1111-111111111111");

        when(dbConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("id")).thenReturn(uuidString);
        when(resultSet.getString("role")).thenReturn("mentor");
        when(mentorDAO.get(UUID.fromString(uuidString))).thenReturn(null);

        LoginDAO loginDAO = new LoginDAO(dbConnection, mentorDAO, null, null);
        loginDAO.login(email, password);

        verify(dbConnection).connect();
        verify(preparedStatement).setString(1, email);
        verify(preparedStatement).setString(2, password);
        verify(preparedStatement, times(2)).setString(anyInt(), anyString());
        verify(mentorDAO).get(uuid);
    }
}