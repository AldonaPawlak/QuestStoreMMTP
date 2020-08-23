package org.example.model;

public class StudentClass {

    private int classID;
    private int studentID;

    public StudentClass(int classID, int studentID) {
        this.classID = classID;
        this.studentID = studentID;
    }

    public int getClassID() {
        return classID;
    }

    public int getStudentID() {
        return studentID;
    }
}
