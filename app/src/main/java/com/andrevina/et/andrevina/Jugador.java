package com.andrevina.et.andrevina;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int intentos;
    private String rutaFoto;
    static ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

    public Jugador(String nombre, int intentos, String rutaFoto) {
        this.nombre = nombre;
        this.intentos = intentos;
        this.rutaFoto = rutaFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIntentos() {
        return intentos;
}

    public String getRutaFoto() {
        return rutaFoto;
    }

}
