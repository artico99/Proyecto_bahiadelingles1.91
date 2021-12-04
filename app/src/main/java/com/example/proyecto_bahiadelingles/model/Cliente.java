package com.example.proyecto_bahiadelingles.model;

public class Cliente
{
    int id;
    String nombre;
    String apellido;
    String rut;
    String telefono;
    String correo;
    String numeroLoft;
    String comentario;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellido, String rut, String telefono, String correo, String numeroLoft, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.telefono = telefono;
        this.correo = correo;
        this.numeroLoft = numeroLoft;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroLoft() {
        return numeroLoft;
    }

    public void setNumeroLoft(String numeroLoft) {
        this.numeroLoft = numeroLoft;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
