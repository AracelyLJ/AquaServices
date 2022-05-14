package com.example.aquaservices.models;

public class Maquina {
    private String id;
    private String nombre;
    private String alias;
    private String imagen;
    private String renta;
    private String obseraciones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRenta() {
        return renta;
    }

    public void setRenta(String renta) {
        this.renta = renta;
    }

    public String getObseraciones() {
        return obseraciones;
    }

    public void setObseraciones(String obseraciones) {
        this.obseraciones = obseraciones;
    }
}
