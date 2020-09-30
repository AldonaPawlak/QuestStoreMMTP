package org.example.handlers;

import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.DAO.MentorDAO;
import org.example.model.Mentor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MentorHandlerTest {

    @Test
    void should_throwAbsenceOfRecordsException_when_editMentorSurname() throws AbsenceOfRecordsException {
        MentorDAO mentorDAO = mock(MentorDAO.class);
        String userDetailsID = "123e4567-e89b-12d3-a456-426614174000";
        String message = "no expected record";
        Exception exception = new AbsenceOfRecordsException();

        when(mentorDAO.get(UUID.fromString(userDetailsID))).thenThrow(exception);

        Assertions.assertTrue(exception.getMessage().contains(message));

    }

}