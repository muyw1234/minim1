package edu.upc.dsa.models;

import java.util.UUID;

public class Lector {
    String lectorId;
    String nombrelector;
    String apellido;
    String dni;
    String fechaNacimiento;
    String LugarDeNacimiento;
    String email;

    public Lector(String nombrelector, String apellido,
                  String dni,String fechaNacimiento,
                  String lugarDeNacimiento, String email) {
        this.lectorId = UUID.randomUUID().toString();
        this.nombrelector = nombrelector;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.LugarDeNacimiento = lugarDeNacimiento;
        this.email = email;
    }

    public Lector() {}

    public String getLectorId() {
        return lectorId;
    }
    public String getNombrelector() {
        return nombrelector;
    }
    public void setNombrelector(String nombrelector) {
        this.nombrelector = nombrelector;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getLugarDeNacimiento() {
        return LugarDeNacimiento;
    }
    public void setLugarDeNacimiento(String lugarDeNacimiento) {
        LugarDeNacimiento = lugarDeNacimiento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



}
