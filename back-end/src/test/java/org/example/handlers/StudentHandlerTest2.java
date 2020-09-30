package org.example.handlers;

import org.example.DAO.StudentDAO;
import org.example.DAO.UserDAO;
import org.example.config.PasswordCrypter;
import org.example.model.Student;
import org.example.model.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void should_add_student(){
        //given

//        List<User> students = Arrays.asList(new Student("Ala", "ala@ala.pl"), new Student("Ola", "ola@ola.pl"));
        User user = new Student("Tomek", "tom@tom.pl");
        doNothing().when(userDAO).add(user);



        //when


        //then



    }



}
//    void addStudent() {
//        User user = new Student(UUID.randomUUID(), "Name", "Surname", "mail@mail.com",
//                PasswordCrypter.crypter("password"), UUID.fromString("745792a7-681b-4efe-abdd-ca027678b397"),
//                true, "444 222 000", "student", UUID.randomUUID(), 0);
//        userDAO.add(user);
//    }