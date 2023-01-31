package com.example.lectorbarras.clases;

public class ConteoMensual {
    private int id;
    private String fecha;
    private String idenMB;
    private String typeMB;
    private String descriptionMB;
    private String locationMB;

    public ConteoMensual() {
        this.id = id;
        this.fecha = fecha;
        this.idenMB = idenMB;
        this.typeMB = typeMB;
        this.descriptionMB = descriptionMB;
        this.locationMB = locationMB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdenMB() {
        return idenMB;
    }

    public void setIdenMB(String idenMB) {
        this.idenMB = idenMB;
    }

    public String getTypeMB() {
        return typeMB;
    }

    public void setTypeMB(String typeMB) {
        this.typeMB = typeMB;
    }

    public String getDescriptionMB() {
        return descriptionMB;
    }

    public void setDescriptionMB(String descriptionMB) {
        this.descriptionMB = descriptionMB;
    }

    public String getLocationMB() {
        return locationMB;
    }

    public void setLocationMB(String locationMB) {
        this.locationMB = locationMB;
    }
}
