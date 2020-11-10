/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Jarod
 */
public class Movimiento {
    
    private int fila;
    private int columna;
    private int puntos;
    private Ficha ficha;
    

    public Movimiento(int fila, int columna, int puntos, Ficha ficha) {
        this.fila = fila;
        this.columna = columna;
        this.puntos = puntos;
        this.ficha = ficha;
    }
  
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    
    public void setPar(int x, int y) {
        this.setColumna(x);
        this.setFila(y);
    }
    
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public void incPuntos(int mas){
        this.puntos+=mas;
    }
}
