package com.example.proyecto_bahiadelingles.model;

public class Loft
{
    public int id;
    public String nombre;
    public String comentario;

    public Loft(){}

    public Loft(String nombre, String comentario, int id) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.id = id;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
