package com.andrevina.et.andrevina;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int intentos;
    static ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

    public Jugador(String nombre, int intentos) {
        this.nombre = nombre;
        this.intentos = intentos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIntentos() {
        return intentos;
}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
