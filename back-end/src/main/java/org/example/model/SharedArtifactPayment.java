package org.example.model;

public class SharedArtifactPayment {

    private int id;
    private int studentID;
    private int studentArtifactID;
    private int payment;

    public SharedArtifactPayment(int id, int studentID, int studentArtifactID, int payment) {
        this.id = id;
        this.studentID = studentID;
        this.studentArtifactID = studentArtifactID;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getStudentArtifactID() {
        return studentArtifactID;
    }

    public int getPayment() {
        return payment;
    }
}
