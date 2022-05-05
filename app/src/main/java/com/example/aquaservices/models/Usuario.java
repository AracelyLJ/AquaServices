package com.example.aquaservices.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String email;
    private String nombre;
    private String pwd;
    private boolean logged;
    private String idRegistro;
    private String porDepositar;
    private String contDeposito;
    private String rol;
    private List<String> sucursalesAsignadas;
    private List<String> sucursalesRegistradas;
    private String telefono;
    private List<String> temp_faltantes;
    private List<String> temp_registradas;

    public Usuario() {

    }

    public Usuario(String id, String email, String nombre, String pwd, boolean logged, String idRegistro, String porDepositar, String contDeposito, String rol, List<String> sucursalesAsignadas, List<String> sucursalesRegistradas, String telefono, List<String> temp_faltantes, List<String> temp_registradas) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.pwd = pwd;
        this.logged = logged;
        this.idRegistro = idRegistro;
        this.porDepositar = porDepositar;
        this.contDeposito = contDeposito;
        this.rol = rol;
        this.sucursalesAsignadas = sucursalesAsignadas;
        this.sucursalesRegistradas = sucursalesRegistradas;
        this.telefono = telefono;
        this.temp_faltantes = temp_faltantes;
        this.temp_registradas = temp_registradas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getPorDepositar() {
        return porDepositar;
    }

    public void setPorDepositar(String porDepositar) {
        this.porDepositar = porDepositar;
    }

    public String getContDeposito() {
        return contDeposito;
    }

    public void setContDeposito(String contDeposito) {
        this.contDeposito = contDeposito;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<String> getSucursalesAsignadas() {
        return sucursalesAsignadas;
    }

    public void setSucursalesAsignadas(List<String> sucursalesAsignadas) {
        this.sucursalesAsignadas = sucursalesAsignadas;
    }

    public List<String> getSucursalesRegistradas() {
        return sucursalesRegistradas;
    }

    public void setSucursalesRegistradas(List<String> sucursalesRegistradas) {
        this.sucursalesRegistradas = sucursalesRegistradas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getTemp_faltantes() {
        return temp_faltantes;
    }

    public void setTemp_faltantes(List<String> temp_faltantes) {
        this.temp_faltantes = temp_faltantes;
    }

    public List<String> getTemp_registradas() {
        return temp_registradas;
    }

    public void setTemp_registradas(List<String> temp_registradas) {
        this.temp_registradas = temp_registradas;
    }
}
