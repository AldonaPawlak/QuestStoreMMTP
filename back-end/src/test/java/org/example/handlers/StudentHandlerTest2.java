package org.example.handlers;

import org.example.DAO.StudentDAO;
import org.example.DAO.UserDAO;
import org.example.model.Student;
import org.example.model.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentHandlerTest2 {

    UserDAO userDAO;
    StudentDAO studentDAO;
    StudentHandler studentHandler;

    @BeforeEach
    public void init(){
        studentDAO = mock(StudentDAO.class);
        userDAO = mock(UserDAO.class);
        studentHandler = new StudentHandler(studentDAO, userDAO);
    }


    @Test
    public void should_return_all_students(){
        //given
        when(userDAO.getAll()).thenReturn(prepareMockData());

        //when
        List<User> students = studentHandler.getStudents();

        //then

        Assertions.assertAll(
                () -> Assert.assertThat(students, Matchers.hasSize(2)),
                () -> assertNotEquals(students.size(), 1),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> students.get(5), "should throw when index out of bounds")
        );


    }

    public List<User> prepareMockData(){
        List<User> students = new ArrayList<>();
        students.add(new Student("Ala", "ala@ala.pl"));
        students.add(new Student("Ola", "ola@ola.pl"));
        return students;
    }

}
