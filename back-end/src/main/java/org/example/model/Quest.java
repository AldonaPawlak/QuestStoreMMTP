package org.example.model;

public class Quest {

    private  int id;
    private  String name;
    private String description;
    private int value;

    public Quest(int id, String name, String description, int value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
