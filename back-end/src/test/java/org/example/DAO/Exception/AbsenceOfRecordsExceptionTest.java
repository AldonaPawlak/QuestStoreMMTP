package org.example.DAO.Exception;

import org.example.DAO.MentorDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AbsenceOfRecordsExceptionTest {

    @Test
    void should_throwAbsenceOfRecordsException() throws AbsenceOfRecordsException {

        MentorDAO mentorDAO = mock(MentorDAO.class);
        String userDetailsID = "123e4567-e89b-12d3-a456-426614174000";
        AbsenceOfRecordsException exception = new AbsenceOfRecordsException();
        String expectedMessage = "There is no expected record in data given.";

        when(mentorDAO.get(UUID.fromString(userDetailsID))).thenThrow(exception);

        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));

    }

}