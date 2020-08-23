package org.example.model;

public class MentorClass {

    private  int classID;
    private  int mentorID;

    public MentorClass(int classID, int mentorID) {
        this.classID = classID;
        this.mentorID = mentorID;
    }

    public int getClassID() {
        return classID;
    }

    public int getMentorID() {
        return mentorID;
    }
}
