package org.example.model;

public class Mentor extends User {

    private int mentorID;

    public Mentor(int id, String name, String surname, String email, String password, int roleID, int mentorID, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.mentorID = mentorID;
    }
}
