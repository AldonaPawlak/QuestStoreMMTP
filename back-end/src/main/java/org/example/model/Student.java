package org.example.model;

import java.util.UUID;

public class Student extends User {

    private UUID studentID;
    private int coins;

    public Student(UUID userDetailsID, String name, String surname, String email, String password, UUID roleID, boolean isActive, String phoneNumber, UUID studentID, int coins) {
        super(userDetailsID, name, surname, email, password, roleID, isActive, phoneNumber);
        this.studentID = studentID;
        this.coins = coins;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public int getCoins() {
        return coins;
    }

}
