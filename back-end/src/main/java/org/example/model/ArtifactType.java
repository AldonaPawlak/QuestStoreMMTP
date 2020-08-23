package org.example.model;

public class ArtifactType {

    private  int id;
    private  String name;

    public ArtifactType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
