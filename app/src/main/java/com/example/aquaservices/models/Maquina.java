package com.example.aquaservices.models;

public class Maquina {
    private String id;
    private String contador;
    private String sucursal;
    private String tipo;

    public Maquina(String id) {
        this.id = id;
        this.contador = "0";
        this.sucursal = "0";
        this.tipo = "garrafon";
    }

    public Maquina(String id, String contador, String sucursal, String tipo) {
        this.id = id;
        this.contador = contador;
        this.sucursal = sucursal;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "id='" + id + '\'' +
                ", contador='" + contador + '\'' +
                ", sucursal='" + sucursal + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
