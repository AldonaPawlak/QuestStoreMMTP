package org.example.model;

import java.util.UUID;

public class StudentArtifact {

    private UUID id;
    private  UUID artifactID;
    private boolean status;
    private  UUID studentID;

    public StudentArtifact(UUID id, UUID artifactID, boolean status, UUID studentID) {
        this.id = id;
        this.artifactID = artifactID;
        this.status = status;
        this.studentID = studentID;
    }

    public UUID getId() {
        return id;
    }

    public UUID getArtifactID() {
        return artifactID;
    }

    public boolean isStatus() {
        return status;
    }

    public UUID getStudentID() {
        return studentID;
    }
}
