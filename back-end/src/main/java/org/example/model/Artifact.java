package org.example.model;

import java.util.UUID;

public class Artifact {

    private UUID id;
    private String name;
    private int price;
    private  UUID categoryID;
    private  String description;
    private  UUID artifactTypeID;

    public Artifact(UUID id, String name, int price, UUID categoryID, String description, UUID artifactTypeID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.artifactTypeID = artifactTypeID;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public UUID getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public UUID getArtifactTypeID() {
        return artifactTypeID;
    }
}
