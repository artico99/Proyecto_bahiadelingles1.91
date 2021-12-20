package com.example.proyecto_bahiadelingles.model;

public class Loft
{
    public int id;
    public String num;
    public String nombre;
    public int luz;
    public int agua;
    public int gas;
    public int reservado;
    public String Estado;

    public Loft(){}

    public Loft(int id, String num, String nombre, int luz, int agua, int gas, int reservado, String estado) {
        this.id = id;
        this.num = num;
        this.nombre = nombre;
        this.luz = luz;
        this.agua = agua;
        this.gas = gas;
        this.reservado = reservado;
        Estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLuz() {
        return luz;
    }

    public void setLuz(int luz) {
        this.luz = luz;
    }

    public int getAgua() {
        return agua;
    }

    public void setAgua(int agua) {
        this.agua = agua;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
