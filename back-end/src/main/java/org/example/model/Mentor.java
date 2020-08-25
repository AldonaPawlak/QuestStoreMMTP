package org.example.model;

import java.util.UUID;

public class Mentor extends User {

    private UUID mentorID;

    public Mentor(UUID id, String name, String surname, String email, String password, UUID roleID, UUID mentorID, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.mentorID = mentorID;
    }
}
