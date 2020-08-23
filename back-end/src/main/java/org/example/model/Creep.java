package org.example.model;

public class Creep extends User {

    private int creepID;

    public Creep(int id, String name, String surname, String email, String password, int roleID, int creepID, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.creepID = creepID;
    }
}
