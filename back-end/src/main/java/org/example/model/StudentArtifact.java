package org.example.model;

public class StudentArtifact {

    private  int id;
    private  int artifactID;
    private boolean status;
    private  int studentID;

    public StudentArtifact(int id, int artifactID, boolean status, int studentID) {
        this.id = id;
        this.artifactID = artifactID;
        this.status = status;
        this.studentID = studentID;
    }

    public int getId() {
        return id;
    }

    public int getArtifactID() {
        return artifactID;
    }

    public boolean isStatus() {
        return status;
    }

    public int getStudentID() {
        return studentID;
    }
}
