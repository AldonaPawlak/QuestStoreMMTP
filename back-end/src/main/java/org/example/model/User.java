package org.example.model;

import java.util.UUID;

public abstract class User {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UUID roleID;
    private boolean isActive;

    public User(UUID id, String name, String surname, String email, String password, UUID roleID, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UUID getRoleID() {
        return roleID;
    }

    public boolean isActive() {
        return isActive;
    }

}
