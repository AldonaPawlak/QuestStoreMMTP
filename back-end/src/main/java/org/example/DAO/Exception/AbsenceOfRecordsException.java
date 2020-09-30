package org.example.DAO.Exception;

public class AbsenceOfRecordsException extends Exception{

    String message = "There is no expected record in data given.";

    @Override
    public String getMessage() {
        return message;
    }
}


