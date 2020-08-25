package org.example.model;

import java.util.UUID;

public class Mentor extends User {

    private UUID mentorID;

    public Mentor(UUID id, String name, String surname, String email, String password, UUID roleID, boolean isActive, UUID mentorID) {
        super(id, name, surname, email, password, roleID, isActive);
        this.mentorID = mentorID;
    }

    public UUID getMentorID() {
        return mentorID;
    }

}
