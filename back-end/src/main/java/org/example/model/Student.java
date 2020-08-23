package org.example.model;

public class Student extends User{

    private int studentID;
    private int userDetailsID;
    private int coins;

    public Student(int id, String name, String surname, String email, String password, int roleID, int studentID, int userDetailsID, int coins, boolean isActive) {
        super(id, name, surname, email, password, roleID, isActive);
        this.studentID = studentID;
        this.userDetailsID = userDetailsID;
        this.coins = coins;
    }
}
