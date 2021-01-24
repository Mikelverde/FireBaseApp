package com.example.firebase.modelo;

public class Persona {
    private String uid;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contraseña;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Persona(String uid, String nombre, String apellidos, String correo, String contraseña) {
        this.uid = uid;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña=contraseña;
    }

    public Persona() {
    }

    @Override
    public String toString() {
        return (nombre+" "+apellidos);
    }
}
