package org.example.model;

import java.util.UUID;

public class Creep extends User {

    public Creep(UUID userDetailsID, String name, String surname, String email, String password, String role,
                 boolean isActive, String phoneNumber) {
        super(userDetailsID, name, surname, email, password, role, isActive, phoneNumber);
    }

}
