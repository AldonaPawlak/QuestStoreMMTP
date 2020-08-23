package org.example.model;

public class Mentor extends User {

    int mentorID;

    public Mentor(int id, String name, String surname, String email, String password, int roleID, int mentorID) {
        super(id, name, surname, email, password, roleID);
        this.mentorID = mentorID;
    }
}
