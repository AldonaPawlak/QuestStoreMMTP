package org.example.handlers;

import org.example.DAO.QuestDAO;
import org.example.DAO.StudentDAO;
import org.example.DAO.UserDAO;
import org.example.model.Student;
import org.example.model.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class StudentHandlerTest2 {

    List<User> students;
    UserDAO userDAO;
    StudentDAO studentDAO;


    StudentHandler studentHandler;

    @BeforeEach
    public void init(){
        studentDAO = mock(StudentDAO.class);
        userDAO = mock(UserDAO.class);
        studentHandler = new StudentHandler(studentDAO, userDAO);
//        students = prepareMockData();
    }


    @Test
    public void testGetStudents(){
        when(userDAO.getAll()).thenReturn(prepareMockData());
        List<User> students = studentHandler.getStudents();

        //then
        Assert.assertThat(students, Matchers.hasSize(2));
    }

    public List<User> prepareMockData(){
        List<User> students = new ArrayList<>();
        students.add(new Student("Ala", "ala@ala.pl"));
        students.add(new Student("Ola", "ola@ola.pl"));
        return students;
    }

}

//    public List<User> getStudents(){
//        return userDAO.getAll();
//    }