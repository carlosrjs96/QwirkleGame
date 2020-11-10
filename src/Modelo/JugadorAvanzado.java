/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Jarod
 */
public class JugadorAvanzado extends Jugador {

    public JugadorAvanzado(Ficha[][] matrizFichas) {
        super(matrizFichas);
    }

    public Jugada getMejorJugada() {
        ArrayList<Jugada> jugadas = getTodasLasJugadas();
        //System.out.println("tamaño de todas las jugadas " + jugadas.size());

        noRegalarQwirkle(jugadas);

        if (jugadas.size() == 0) {
            return null;
        }

        Jugada mayor = jugadas.get(0);

        if (jugadas.size() > 0) {
            for (int i = 0; i < jugadas.size(); i++) {
                //System.out.println("puntaje de " + i + " jugada : " +  jugadas.get(i).getPuntajeTotal()) ;
                if (mayor.getPuntajeTotal() < jugadas.get(i).getPuntajeTotal()) {
                    //System.out.println("ANTES -> MAYOR: " + mayor.getPuntajeTotal() + " -> JUGADA MAYOR : " +  jugadas.get(i).getPuntajeTotal());
                    mayor = jugadas.get(i);
                    //System.out.println("DESPUES -> MAYOR: " + jugadas.get(i).getPuntajeTotal() + " -> JUGADA MENOS : " +  mayor.getPuntajeTotal());
                }
            }
        }
        /*
        System.out.println("********JUGADA CON MAS PUNTOS*********");
        for (Movimiento movimiento : mayor.getMovimientos()) {
            System.out.println(movimiento.getFicha().getTipo() + " fila : " + movimiento.getFila() + " col : "
                    + movimiento.getColumna() + " p : " + movimiento.getPuntos());
        }
        System.out.println("Total de puntos: " + mayor.getPuntajeTotal());
        System.out.println("**************************************");
         */

 
        if (mayor.getPuntajeTotal()<0){
            return null;
        }
         
        return mayor;
    }
    
    //entran todas las jugadas posibles y elimina todas aquelllas
    //que formen filas o columnas de 5, que regalen un qwirkle
    private void noRegalarQwirkle(ArrayList<Jugada> jugadas) {
        ArrayList<Jugada> remover = new ArrayList();
        for (int i = 0; i < jugadas.size(); i++) {
            if (regalaQwirkle(jugadas.get(i).getMovimientos())) {
                remover.add(jugadas.get(i));
            }
        }
        for (int i = 0; i < remover.size(); i++) {
            jugadas.remove(remover.get(i));
        }
    }

    private boolean regalaQwirkle(ArrayList<Movimiento> movimientos) {
        ponerFichas(movimientos);//setea los movimientos en la matriz
        for (Movimiento movimiento : movimientos) {
            //si encuantra que regala un quirkle en fila o columna retorna true
            if (regalaQwirkleFila(movimiento.getFila(), movimiento.getColumna())
                    || regalaQwirkleColumna(movimiento.getFila(), movimiento.getColumna())) {
                quitarFichas(movimientos);//quita los movimientos de la matriz
                return true;
            }
        }
        quitarFichas(movimientos);//quita los movimientos de la matriz
        return false;
    }

    private boolean regalaQwirkleFila(int fila, int columna) {
        int columTmp = columna;
        //hacer que se vaya al indice menor donde haya una ficha
        while (this.matrizFichas[fila][columTmp - 1] != null) {
            columTmp--;
        }
        int contador = 1;
        //empieza a recorrer desde el indice inferior hasta que encuentra un null
        while (this.matrizFichas[fila][columTmp + 1] != null) {
            columTmp++;
            contador++;
        }
        return contador==5 ;//si el contador es 5 es porque regala un qwirkle
    }

    private boolean regalaQwirkleColumna(int fila, int columna) {
        int filaTmp = fila;
        while (this.matrizFichas[filaTmp - 1][columna] != null) {
            filaTmp--;
        }
        int contador = 1;
        while (this.matrizFichas[filaTmp + 1][columna] != null) {
            filaTmp++;
            contador++;
        }
        return contador == 5;
    }

}
