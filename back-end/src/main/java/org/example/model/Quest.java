package org.example.model;

import java.util.UUID;

public class Quest {

    private UUID id;
    private  String name;
    private String description;
    private int value;

    public Quest(UUID id, String name, String description, int value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public UUID getId() {
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
