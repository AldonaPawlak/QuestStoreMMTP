package org.example.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.DAO.StudentDAO;
import org.example.DAO.UserDAO;
import org.example.model.Student;
import org.example.model.User;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentHandlerTest {

    UserDAO userDAO;
    StudentDAO studentDAO;
    StudentHandler studentHandler;
    ObjectMapper mapper;

    UUID uuid = UUID.fromString("11111111-1111-1111-1111-111111111111");
    String uuidString = "11111111-1111-1111-1111-111111111111";
    Student student = new Student("Ala", "ala@ala.pl");
    List<User> students = Arrays.asList(new Student("Ala", "ala@ala.pl"),
            new Student("Ola", "ola@ola.pl"));
    List<User> students2 = Arrays.asList(new Student("Ala", "ala@ala.pl"),
            new Student("Tomek", "tomek@tomek.pl"));
    String availableStudentsAsJson = "[{\"name\":\"Ala\",\"email\":\"ala@ala.pl\"}," +
            "{\"name\":\"Ola\",\"email\":\"ola@ola.pl\"}]";

    @BeforeEach
    public void init(){
        studentDAO = mock(StudentDAO.class);
        userDAO = mock(UserDAO.class);
        mapper = mock(ObjectMapper.class);
        studentHandler = new StudentHandler(studentDAO, userDAO, mapper);
    }

    @Test
    void should_displayAllStudents_when_get_method() throws IOException {
        // Arrange
        HttpExchange exchange = mock(HttpExchange.class);
        when(exchange.getRequestMethod()).thenReturn("GET");
        Headers headers = new Headers();
        when(exchange.getResponseHeaders()).thenReturn(headers);
        doNothing().when(exchange).sendResponseHeaders(200, availableStudentsAsJson.getBytes().length);
        OutputStream os = mock(OutputStream.class);
        when(exchange.getResponseBody()).thenReturn(os);
        when(userDAO.getAll()).thenReturn(students);
        when(mapper.writeValueAsString(students)).thenReturn(availableStudentsAsJson);

        // Act
        studentHandler.handle(exchange);

        // Assert
        Assertions.assertAll(
                () -> verify(exchange, times(1)).getRequestMethod(),
                () -> assertNotNull(headers.get("Access-Control-Allow-Methods")),
                () -> verify(exchange).sendResponseHeaders(200, availableStudentsAsJson.getBytes().length),
                () -> verify(os).write(availableStudentsAsJson.getBytes()),
                () -> verify(os).close()
        );
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
    public void should_makeJSON_from_studentsList() throws Exception {
        //given


        when(mapper.writeValueAsString(students)).thenReturn(availableStudentsAsJson);


        //then
        Assertions.assertAll(
                () -> assertEquals(availableStudentsAsJson, studentHandler.makeJSONFromStudents(students)),
                () -> assertNotEquals(availableStudentsAsJson, studentHandler.makeJSONFromStudents(students2))
        );
    }

    @Test
    public void should_invoke_remove_student() throws Exception {
        //given
        when(studentDAO.get(uuid)).thenReturn(student);

        //when
        studentHandler.removeStudent(uuidString);

        //then
        verify(studentDAO).remove(student);
    }

    @Test
    public void should_edit_studentName_when_passed_data() throws Exception {
        //given
        String newName = "Eva";
        when(studentDAO.get(uuid)).thenReturn(student);
        student.setName(newName);
        //when
        studentHandler.editStudentName(uuidString, newName);
        //then
        verify(studentDAO).edit(student);
    }

    @Test
    public void should_edit_studentSurnbame_when_passed_data() throws Exception {
        //given
        String newSurname = "Kowalsky";
        when(studentDAO.get(uuid)).thenReturn(student);
        student.setName(newSurname);
        //when
        studentHandler.editStudentSurname(uuidString, newSurname);
        //then
        verify(studentDAO).edit(student);
    }

    @Test
    public void should_edit_studentMail_when_passed_data() throws Exception {
        //given
        String email = "eva@eva.pl";
        when(studentDAO.get(uuid)).thenReturn(student);
        student.setName(email);
        //when
        studentHandler.editStudentMail(uuidString, email);
        //then
        verify(studentDAO).edit(student);
    }

    @Test
    public void should_edit_studentPhone_when_passed_data() throws Exception {
        //given
        String phone = "666-666-666";
        when(studentDAO.get(uuid)).thenReturn(student);
        student.setName(phone);
        //when
        studentHandler.editStudentPhone(uuidString, phone);
        //then
        verify(studentDAO).edit(student);
    }





}
