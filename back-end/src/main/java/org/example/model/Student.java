package org.example.model;

import java.util.UUID;

public class Student extends User {

    private int coins;

    public Student(UUID userDetailsID, String name, String surname, String email, String password, String role,
                   boolean isActive, String phoneNumber, int coins) {
        super(userDetailsID, name, surname, email, password, role, isActive, phoneNumber);
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

}
