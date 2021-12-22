package com.example.proyecto_bahiadelingles.model;

public class Cliente
{
    int id;
    String nombre;
    String apellido;
    String rut;
    String fechaEntrada;
    String fechaSalida;
    String telefono;
    String correo;
    String numeroLoft;
    String comportamiento;
    String comentario;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellido, String rut, String fechaEntrada, String fechaSalida, String telefono, String correo, String numeroLoft, String comportamiento, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.telefono = telefono;
        this.correo = correo;
        this.numeroLoft = numeroLoft;
        this.comportamiento = comportamiento;
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

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
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

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
