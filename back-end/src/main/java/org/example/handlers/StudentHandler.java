package org.example.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.DAO.DAO;
import org.example.DAO.DBConnection;
import org.example.DAO.Exception.AbsenceOfRecordsException;
import org.example.DAO.StudentDAO;
import org.example.config.PasswordCrypter;
import org.example.model.Student;
import org.example.model.User;
import org.example.services.DecoderURL;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class StudentHandler implements HttpHandler {

    private StudentDAO studentDAO;
    private DAO<User> userDAO;
    private List<User> students;
    private ObjectMapper mapper;

    public StudentHandler(StudentDAO studentDAO, DAO<User> userDAO, ObjectMapper mapper) {
        this.studentDAO = studentDAO;
        this.userDAO = userDAO;
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";

        int status = 200;
        try {
            if (method.equals("GET")) {
//                response = getStudents();
                students = getStudents();
                response = makeJSONFromStudents(students);
                ResponseHelper.sendResponse(response, exchange, status);
            }
            if (method.equals("POST")) {
                String url = exchange.getRequestURI().getRawPath();
                String[] urlParts = url.split("/");
                String userDetailsID = urlParts[2];
                if (urlParts[3].equals("remove")) {
                    removeStudent(userDetailsID);
                }
                if (urlParts[3].equals("edit-name")) {
                    editStudentName(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if (urlParts[3].equals("edit-surname")) {
                    editStudentSurname(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if (urlParts[3].equals("edit-mail")) {
                    editStudentMail(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if (urlParts[3].equals("edit-phone")) {
                    editStudentPhone(urlParts[2], DecoderURL.polishDecoder(urlParts[4]));
                }
                if (urlParts[3].equals("add")) {
                    addStudent();
                }
//                response = getStudents();
                response = makeJSONFromStudents(students);
                ResponseHelper.sendResponse(response, exchange, status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(404, response.getBytes().length);
        }
    }

//    private String getStudents() throws JsonProcessingException {
//        List<User> students = userDAO.getAll();
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(students);
//    }

    public List<User> getStudents(){
        return userDAO.getAll();
    }

    public String makeJSONFromStudents(List<User> students) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(students);
    }

    void removeStudent(String userDetailsID) throws Exception {
        Student student = studentDAO.get(UUID.fromString(userDetailsID));
        studentDAO.remove(student);
    }

    void editStudentName(String userDetailsID, String newName) throws Exception {
        Student student = studentDAO.get(UUID.fromString(userDetailsID));
        student.setName(newName);
        studentDAO.edit(student);
    }

    void editStudentSurname(String userDetailsID, String newSurname) throws AbsenceOfRecordsException {
        Student student = studentDAO.get(UUID.fromString(userDetailsID));
        student.setSurname(newSurname);
        studentDAO.edit(student);
    }

    void editStudentMail(String userDetailsID, String newMail) throws AbsenceOfRecordsException {
        Student student = studentDAO.get(UUID.fromString(userDetailsID));
        student.setEmail(newMail);
        studentDAO.edit(student);
    }

    void editStudentPhone(String userDetailsID, String newPhone) throws AbsenceOfRecordsException {
        Student student = studentDAO.get(UUID.fromString(userDetailsID));
        student.setPhoneNumber(newPhone);
        studentDAO.edit(student);
    }

    void addStudent() {
        User user = new Student(UUID.randomUUID(), "Name", "Surname", "mail@mail.com",
                PasswordCrypter.crypter("password"), UUID.fromString("745792a7-681b-4efe-abdd-ca027678b397"),
                true, "444 222 000", "student", UUID.randomUUID(), 0);
        userDAO.add(user);
    }

}

