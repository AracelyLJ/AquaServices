package com.example.aquaservices.models;

import java.util.Map;

public class RegistroMaquina {
    private String id;
    private String alias;
    private String fecha;
    private String hora;
    private String nombre;
    private String ubicacion;
    private Map<String, String> contadores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public Map<String, String> getContadores() {
        return contadores;
    }

    public void setContadores(Map<String, String> contadores) {
        this.contadores = contadores;
    }
}
