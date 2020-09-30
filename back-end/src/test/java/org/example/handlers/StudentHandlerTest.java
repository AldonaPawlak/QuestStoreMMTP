package org.example.handlers;


import org.example.config.PasswordCrypter;
import org.example.model.Student;
import org.example.model.User;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentHandlerTest {

    @Test
    public void getStudents(){

        //given

        StudentHandler studentHandler = mock(StudentHandler.class);
        when(studentHandler.getStudents()).thenReturn(prepareMockData());

        //when
        List<User> students = studentHandler.getStudents();

        //then
        Assert.assertThat(students, Matchers.hasSize(2));

    }

    private List<User> prepareMockData(){
        List<User> students = new ArrayList<>();
        students.add(new Student("Ala", "ala@ala.pl"));
        students.add(new Student("Ola", "ola@ola.pl"));

        return students;
    }

    @Test
    public void testAddStudent(){

        //given

        StudentHandler studentHandler = mock(StudentHandler.class);
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
