package com.example.aquaservices.models;

public class Sucursal {

    private String id;
    private String nombre;
    private String ubicacion;
    private String tipo_renta;
    private String monto_renta;

    public Sucursal(String id, String nombre, String ubicacion, String tipo_renta, String monto_renta) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo_renta = tipo_renta;
        this.monto_renta = monto_renta;
    }

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo_renta() {
        return tipo_renta;
    }

    public void setTipo_renta(String tipo_renta) {
        this.tipo_renta = tipo_renta;
    }

    public String getMonto_renta() {
        return monto_renta;
    }

    public void setMonto_renta(String monto_renta) {
        this.monto_renta = monto_renta;
    }
}
