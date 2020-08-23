package org.example.model;

public abstract class User {

    int id;
    String name;
    String surname;
    String email;
    String password;
    int roleID;

    public User(int id, String name, String surname, String email, String password, int roleID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleID = roleID;
    }

    public int getId() {
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

    public int getRoleID() {
        return roleID;
    }
}
