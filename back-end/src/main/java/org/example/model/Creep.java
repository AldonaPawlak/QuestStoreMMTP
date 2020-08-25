package org.example.model;

import java.util.UUID;

public class Creep extends User {

    private UUID creepID;

    public Creep(UUID id, String name, String surname, String email, String password, UUID roleID, UUID creepID, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.creepID = creepID;
    }
}
