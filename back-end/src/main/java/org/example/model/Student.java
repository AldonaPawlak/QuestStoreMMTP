package org.example.model;

import java.util.UUID;

public class Student extends User {

    private UUID studentID;
    private UUID userDetailsID;
    private int coins;

    public Student(UUID id, String name, String surname, String email, String password, UUID roleID, UUID studentID, UUID userDetailsID, int coins, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.studentID = studentID;
        this.userDetailsID = userDetailsID;
        this.coins = coins;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public UUID getUserDetailsID() {
        return userDetailsID;
    }

    public int getCoins() {
        return coins;
    }
}
