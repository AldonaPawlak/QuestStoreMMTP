package org.example.model;

public class StudentQuest {

    private int id;
    private int questID;
    private  int statusID;
    private int studentID;

    public StudentQuest(int id, int questID, int statusID, int studentID) {
        this.id = id;
        this.questID = questID;
        this.statusID = statusID;
        this.studentID = studentID;
    }

    public int getId() {
        return id;
    }

    public int getQuestID() {
        return questID;
    }

    public int getStatusID() {
        return statusID;
    }

    public int getStudentID() {
        return studentID;
    }
}
