package com.example.lectorbarras.clases;

public class Medios {
    private int id;
    private String IdenMB;
    private String type;
    private String description;
    private String location;

    public Medios() {
        this.id = id;
        this.IdenMB = IdenMB;
        this.type = type;
        this.description = description;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdenMB() {
        return IdenMB;
    }

    public void setIdenMB(String IdenMB) {
        this.IdenMB = IdenMB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
