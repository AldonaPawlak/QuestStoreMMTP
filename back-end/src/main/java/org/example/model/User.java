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
    private  String phoneNumber;

    public User(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID, boolean isActive, String phoneNumber) {
        this.userDetailsID = userDetailsID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
