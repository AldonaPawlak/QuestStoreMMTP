package org.example.model;

public class Creep extends User {

    int creepID;

    public Creep(int id, String name, String surname, String email, String password, int roleID, int creepID) {
        super(id, name, surname, email, password, roleID);
        this.creepID = creepID;
    }
}
