package org.example.model;

import java.util.UUID;

public abstract class User {

    private UUID userDetailsID;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UUID roleID;
    private boolean isActive;

    public User(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID, boolean isActive) {
        this.userDetailsID = userDetailsID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.isActive = isActive;
    }

    public UUID getUserDetailsID() {
        return userDetailsID;
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
