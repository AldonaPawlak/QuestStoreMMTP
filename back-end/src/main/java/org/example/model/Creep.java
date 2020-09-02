package org.example.model;

import java.util.UUID;

public class Creep extends User {

    private UUID creepID;

    public Creep(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID, boolean isActive, String phoneNumber, UUID creepID) {
        super(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber);
        this.creepID = creepID;
    }

    public UUID getCreepID() {
        return creepID;
    }
}
