package org.example.model;

import java.util.UUID;

public class Mentor extends User {

    public Mentor(UUID userDetailsID, String name, String surname, String email, String password, String role,
                  boolean isActive, String phoneNumber) {
        super(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber);
    }

}
