package org.example.model;

import java.util.UUID;

public class Student extends User {

    private UUID studentID;
    private int coins;

    public Student(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID,
                   boolean isActive, String phoneNumber, String role, UUID studentID, int coins) {
        super(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber, role);
        this.studentID = studentID;
        this.coins = coins;
    }

    // for tests
    public Student(String name, String email){
        super(name, email);
    };

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public UUID getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + super.getName() +
                ", email=" + super.getEmail() +
                '}';
    }
}