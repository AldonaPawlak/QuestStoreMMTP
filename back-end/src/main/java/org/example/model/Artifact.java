package org.example.model;

public class Artifact {

    private int id;
    private String name;
    private int price;
    private  int categoryID;
    private  String description;
    private  int artifactTypeID;

    public Artifact(int id, String name, int price, int categoryID, String description, int artifactTypeID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.description = description;
        this.artifactTypeID = artifactTypeID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getDescription() {
        return description;
    }

    public int getArtifactTypeID() {
        return artifactTypeID;
    }
}
