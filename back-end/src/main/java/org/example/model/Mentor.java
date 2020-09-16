package org.example.model;

import java.util.UUID;

public class Mentor extends User {

    private UUID mentorID;

    public Mentor(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID, boolean isActive, String phoneNumber, UUID mentorID) {
        super(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber);
        this.mentorID = mentorID;
    }



    public UUID getMentorID() {
        return mentorID;
    }

}
