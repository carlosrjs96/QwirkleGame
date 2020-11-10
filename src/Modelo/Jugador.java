/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Carlos
 */
public abstract class Jugador {

    protected double puntaje;
    protected ArrayList<Ficha> fichas;
    protected Ficha[] mano;
    int puntajeUltima;
    int fichasUsadas;

    private JugadaType tipoJugadaActual;
    private OrientacionType orientacion;
    private DireccionType direccionSegundaFicha;
    private Movimiento primerJugada = null;
    private boolean segundaJugada = false;
    private Movimiento ultimaJugada = null;
    protected Ficha[][] matrizFichas; //no se cosideran los bordes de las matriz
    //de 19x15 fichas visibles, asi que 21x17 por que los bordes no se ven

    public Jugador(Ficha[][] matrizFichas) {
        this.puntaje = 0;
        this.fichasUsadas = 0;
        this.puntajeUltima = 0;
        this.fichas = new ArrayList();
        this.mano = new Ficha[6];
        this.matrizFichas = matrizFichas;
    }

    public int getFichasUsadas() {

        return fichasUsadas;
    }

    public void setFichasUsadas(int fichasUsadas) {
        this.fichasUsadas = fichasUsadas;
    }

    public int getPuntajeUltima() {
        return puntajeUltima;
    }

    public void setPuntajeUltima(int puntajeUltima) {
        this.puntajeUltima = puntajeUltima;
    }

    public void sumarPuntaje(int num) {
        puntaje += num;
    }

    public Ficha[] getMano() {
        return mano;
    }

    public void actualizarMano() {
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] == null && fichas.size() > 0) {
                mano[i] = fichas.get(0);
                fichas.remove(mano[i]);
            }
        }
    }

    /*
    elimina la jugada de la mano que utilizaremos en el tablero
    
    public void eliminarDeMano () {
        
    }
     */
    public void addFichas(Ficha ficha) {
        fichas.add(ficha);
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

    //retorna true si cumple figura
    private boolean jugadaDeFigura(Ficha[][] matrizFichas, Ficha ficha, int[] par) {
        //par = [x,y]
        //primero por fila
        //largo de filas
        //retrocede
        boolean is = true;
        for (int i = par[0]; i > 0; i--) {
            //asumiendo que en los espacios donde no hay ficha hay un null
            if (matrizFichas[par[1]][i].equals(null)) {
                break;
            }
            //valida que si hay ficha, si la hay y no coincide el tipo, break
            if (!matrizFichas[par[1]][i].getFigura().equals(ficha.getFigura())) {
                is = false;
                break;
            }
        }
        if (is) {
            //largo de la fila
            //avanza
            for (int i = par[0]; i < matrizFichas[par[1]].length; i++) {
                if (matrizFichas[par[1]][i].equals(null)) {
                    break;
                }
                if (!matrizFichas[par[1]][i].getFigura().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        if (is) {
            //recorre la columna de y hasta 0
            for (int i = par[1]; i > 0; i--) {
                if (matrizFichas[i][par[0]].equals(null)) {
                    break;
                }
                if (!matrizFichas[i][par[0]].getFigura().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        if (is) {

            //avanza en columna desde y hasta el final
            for (int i = par[1]; i < matrizFichas.length; i++) {
                if (matrizFichas[i][par[0]].equals(null)) {
                    break;
                }
                if (!matrizFichas[i][par[0]].getFigura().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        return is;
    }

    //retorna true si cumple figura
    private boolean jugadaDeColor(Ficha[][] matrizFichas, Ficha ficha, int[] par) {
        //par = [x,y]
        //primero por fila
        //largo de filas
        //retrocede
        boolean is = true;
        for (int i = par[0]; i > 0; i--) {
            //asumiendo que en los espacios donde no hay ficha hay un null
            if (matrizFichas[par[1]][i] == null) {
                break;
            }
            //valida que si hay ficha, si la hay y no coincide el tipo, break
            if (!matrizFichas[par[1]][i].getColor().equals(ficha.getFigura())) {
                is = false;
                break;
            }
        }
        if (is) {
            //largo de la fila
            //avanza
            for (int i = par[0]; i < matrizFichas[par[1]].length; i++) {
                if (matrizFichas[par[1]][i] == null) {
                    break;
                }
                if (!matrizFichas[par[1]][i].getColor().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        if (is) {
            //recorre la columna de y hasta 0
            for (int i = par[1]; i > 0; i--) {
                if (matrizFichas[i][par[0]] == null) {
                    break;
                }
                if (!matrizFichas[i][par[0]].getColor().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        if (is) {

            //avanza en columna desde y hasta el final
            for (int i = par[1]; i < matrizFichas.length; i++) {
                if (matrizFichas[i][par[0]] == null) {
                    break;
                }
                if (!matrizFichas[i][par[0]].getColor().equals(ficha.getFigura())) {
                    is = false;
                    break;
                }
            }
        }
        return is;
    }

    public boolean jugadaValida(Ficha[][] matrizFichas, Ficha ficha, int[] par) {
        return jugadaDeColor(matrizFichas, ficha, par) && jugadaDeFigura(matrizFichas, ficha, par);
    }

    //entra a esta funcion cuando se sepa que la jugada es valida
    //true, mismo color
    //false, misma figura
    public boolean determinarTipoJugada(Ficha ficha1, Ficha ficha2) {
        return ficha1.getColor().equals(ficha2.getColor());
    }

    //entra a esta funcion cuando se sepa que la jugada es valida
    //true, x, misma fila
    //false, y, misma columna
    public boolean determinarOrientacion(int[] par1, int[] par2) {
        return par1[0] == par2[0];
    }

    public JugadaType getTipoJugadaActual() {
        return tipoJugadaActual;
    }

    public void setTipoJugadaActual(JugadaType tipoJugadaActual) {
        this.tipoJugadaActual = tipoJugadaActual;
    }

    public OrientacionType getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(OrientacionType orientacion) {
        this.orientacion = orientacion;
    }

    public DireccionType getDireccionSegundaFicha() {
        return direccionSegundaFicha;
    }

    public void setDireccionSegundaFicha(DireccionType direccionSegundaFicha) {
        this.direccionSegundaFicha = direccionSegundaFicha;
    }

    public ArrayList<Movimiento> getListaPrimerosMovimientos(Ficha ficha, ArrayList<Ficha> subconjunto) {
        ArrayList<Movimiento> primerosMovimientos = new ArrayList();
        //System.out.println(matrizFichas.length);
        for (int fila = 0; fila < this.matrizFichas.length; fila++) {
            for (int columna = 0; columna < this.matrizFichas.length; columna++) {
                if (this.matrizFichas[fila][columna] == null) {
                    int puntos = calcularPuntos(ficha, fila, columna, subconjunto);
                    if (puntos > 0) {
                        primerosMovimientos.add(new Movimiento(fila, columna, puntos, ficha));
                    }
                }
            }
        }
        return primerosMovimientos;
    }

    public void backtrackingJugadas(int num, ArrayList<Ficha> perm, ArrayList<Movimiento> solucion, ArrayList<Jugada> arr, Movimiento primerMov, OrientacionType ori) {
        if (num == perm.size()) {
            if (jugadaValida(solucion)) {
                //System.out.println("======Solucion Backtracking======");
                ArrayList<Movimiento> nuevaSolucion = new ArrayList<>();
                int total = 0;
                for (Movimiento movimiento : solucion) {
                    nuevaSolucion.add(movimiento);
                    /*System.out.println(movimiento.getFicha().getTipo() + " fila : " + movimiento.getFila() + " col : "
                            + movimiento.getColumna() + " p : " + movimiento.getPuntos());
                    total += movimiento.getPuntos();*/
                }
                //System.out.println(" Total puntos >>>> " + total);
                //System.out.println("=================================");
                arr.add(new Jugada(nuevaSolucion));
            }
        } else {
            ArrayList<Movimiento> movimientos = getMovimientosValidos(perm.get(num), ori, perm, primerMov);
            for (Movimiento movimiento : movimientos) {
                //if(calcularPuntos(movimiento.getFicha(), movimiento.getFila(), movimiento.getColumna(), perm) > 0){
                solucion.add(movimiento);
                this.matrizFichas[movimiento.getFila()][movimiento.getColumna()] = movimiento.getFicha();
                backtrackingJugadas(num + 1, perm, solucion, arr, primerMov, ori);
                this.matrizFichas[movimiento.getFila()][movimiento.getColumna()] = null;
                solucion.remove(movimiento);
                //}                
            }
        }
    }

    public boolean jugadaValida(ArrayList<Movimiento> solucion) {
        ArrayList<Ficha> perm = new ArrayList<>();
        for (Movimiento movimiento : solucion) {
            perm.add(movimiento.getFicha());
        }
        this.ponerFichas(solucion);
        for (Movimiento movimiento : solucion) {
            if (calcularPuntos(movimiento.getFicha(), movimiento.getFila(), movimiento.getColumna(), perm) <= 0) {
                this.quitarFichas(solucion);
                return false;
            }
        }
        this.quitarFichas(solucion);
        return true;
    }

    public void ponerFichas(ArrayList<Movimiento> solucion) {
        for (Movimiento mov : solucion) {
            this.matrizFichas[mov.getFila()][mov.getColumna()] = mov.getFicha();
        }
    }

    public void quitarFichas(ArrayList<Movimiento> solucion) {
        for (Movimiento mov : solucion) {
            this.matrizFichas[mov.getFila()][mov.getColumna()] = null;
        }
    }

    public ArrayList<Movimiento> getMovimientosValidos(Ficha ficha, OrientacionType orientacion, ArrayList<Ficha> perm, Movimiento primerMov) {
        ArrayList<Movimiento> listMovimientos = new ArrayList<Movimiento>();
        for (int i = 0; i < this.matrizFichas.length; i++) {
            if (orientacion == OrientacionType.COLUMNA) {
                int puntos = calcularPuntos(ficha, i, primerMov.getColumna(), perm);
                if (this.matrizFichas[i][primerMov.getColumna()] == null && puntos > 0) {
                    if (validarEntre(primerMov, orientacion, i)) {
                        listMovimientos.add(new Movimiento(i, primerMov.getColumna(), puntos, ficha));
                    }

                }
            } else {
                int puntos = calcularPuntos(ficha, primerMov.getFila(), i, perm);
                if (this.matrizFichas[primerMov.getFila()][i] == null && puntos > 0) {
                    if (validarEntre(primerMov, orientacion, i)) {
                        listMovimientos.add(new Movimiento(primerMov.getFila(), i, puntos, ficha));
                    }
                }
            }
        }
        return listMovimientos;
    }

    private boolean validarEntre(Movimiento primerMov, OrientacionType orientacion, int num) {
        int mayor, menor;
        switch (orientacion) {
            case COLUMNA:
                mayor = num < primerMov.getFila() ? primerMov.getFila() : num;
//                menor = mayor == primerMov.getFila() ? num : primerMov.getFila();//tener cuidado
                menor = num < primerMov.getFila() ? num : primerMov.getFila();//tener cuidado
                for (int i = menor + 1; i < mayor; i++) {
                    if (this.matrizFichas[i][primerMov.getColumna()] == null) {
                        return false;
                    }
                }
                break;
            case FILA:
                mayor = num < primerMov.getColumna() ? primerMov.getColumna() : num;
//                menor = mayor == primerMov.getColumna() ? num : primerMov.getColumna();//tener cuidado
                menor = num < primerMov.getColumna() ? num : primerMov.getColumna();//tener cuidado
                for (int i = menor + 1; i < mayor; i++) {
                    if (this.matrizFichas[primerMov.getFila()][i] == null) {
                        return false;
                    }
                }
                break;
        }
        return true;

    }

    public ArrayList<Jugada> getListaJugadas(ArrayList<Ficha> subconjunto) { //AQUI LAS PERMUTACIONES DE LA MANO 
        ArrayList<Jugada> jugadas = new ArrayList<>();
        ArrayList<Movimiento> primerosMovimientos = getListaPrimerosMovimientos(subconjunto.get(0), subconjunto);
        if (subconjunto.size() > 1) {
            ArrayList<Jugada> arr = new ArrayList<Jugada>();
            for (Movimiento primerMovimiento : primerosMovimientos) {
                for (OrientacionType orientacion : OrientacionType.values()) {
                    ArrayList<Movimiento> solucion = new ArrayList<Movimiento>();
                    solucion.add(primerMovimiento);
                    this.matrizFichas[primerMovimiento.getFila()][primerMovimiento.getColumna()] = primerMovimiento.getFicha();
                    backtrackingJugadas(1, subconjunto, solucion, arr, primerMovimiento, orientacion);
                    this.matrizFichas[primerMovimiento.getFila()][primerMovimiento.getColumna()] = null;
                }
            }
            /*for (ArrayList<Movimiento> arrMovimientos : arr) {
                jugadas.add(new Jugada(arrMovimientos));
            }*/
            jugadas.addAll(arr);
        } else {
            for (Movimiento movimiento : primerosMovimientos) {
                ArrayList<Movimiento> movimientos = new ArrayList<>();
                movimientos.add(movimiento);
                jugadas.add(new Jugada(movimientos));
            }
        }
        return jugadas;
    }

    public int calcularPuntos(Ficha ficha, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        //System.out.println("F: "+fila+" / C:"+columna);
        if (fila > 0 && columna > 0 && fila < this.matrizFichas.length - 1 && columna < this.matrizFichas.length - 1) {
            int puntosPorFigura = validarFigura(ficha, fila, columna, subconjunto);
            if (puntosPorFigura > 0) {
                puntos += puntosPorFigura;
            }
            int puntosPorColor = validarColor(ficha, fila, columna, subconjunto);
            if (puntosPorColor > 0) {
                puntos += puntosPorColor;
            }
            if (puntos > 0) {
                return puntos;
            }
        }
        return 0;
    }

    public int validarFigura(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) { //determina si la figura calza en la posición
        int puntos = 0;

        if (this.matrizFichas[fila][columna - 1] != null) {
            int izquierda = validarFiguraHaciaLaIzquierda(fichaActual, fila, columna, subconjunto); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos = izquierda >= 0 ? puntos + izquierda : puntos - 1000;
            puntos = izquierda == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila][columna + 1] != null) {
            int derecha = validarFiguraHaciaLaDerecha(fichaActual, fila, columna, subconjunto);;
            puntos = derecha >= 0 ? puntos + derecha : puntos - 1000;
            puntos = derecha == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila - 1][columna] != null) {
            int arriba = validarFiguraHaciaArriba(fichaActual, fila, columna, subconjunto);;
            puntos = arriba >= 0 ? puntos + arriba : puntos - 1000;
            puntos = arriba == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila + 1][columna] != null) {
            int abajo = validarFiguraHaciaAbajo(fichaActual, fila, columna, subconjunto);;
            puntos = abajo >= 0 ? puntos + abajo : puntos - 1000;
            puntos = abajo == 6 ? puntos + 6 : puntos + 0;
        }

        if (puntos > 0) {
            return puntos;
        } else {
            return 0;
        }
    }

    public int validarColor(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) { //determina si la figura calza en la posición
        int puntos = 0;
        //boolean res = false;
        if (this.matrizFichas[fila][columna - 1] != null) {
            int izquierda = validarColorHaciaLaIzquierda(fichaActual, fila, columna, subconjunto); // retorna los puntos y 0 (no hay fichas alrededor)
            puntos = izquierda >= 0 ? puntos + izquierda : puntos - 1000;
            puntos = izquierda == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila][columna + 1] != null) {
            int derecha = validarColorHaciaLaDerecha(fichaActual, fila, columna, subconjunto);
            puntos = derecha >= 0 ? puntos + derecha : puntos - 1000;
            puntos = derecha == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila - 1][columna] != null) {
            int arriba = validarColorHaciaArriba(fichaActual, fila, columna, subconjunto);
            puntos = arriba >= 0 ? puntos + arriba : puntos - 1000;
            puntos = arriba == 6 ? puntos + 6 : puntos + 0;
        }
        if (this.matrizFichas[fila + 1][columna] != null) {
            int abajo = validarColorHaciaAbajo(fichaActual, fila, columna, subconjunto);
            puntos = abajo >= 0 ? puntos + abajo : puntos - 1000;
            puntos = abajo == 6 ? puntos + 6 : puntos + 0;
        }
        if (puntos > 0) {
            return puntos;
        } else {
            return puntos;
        }
    }

    public int validarFiguraHaciaLaIzquierda(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = columna - 1; matrizFichas[fila][i] != null && i > 0; i--) {
            if (matrizFichas[fila][i].getColor() != fichaActual.getColor()
                    || matrizFichas[fila][i].getFigura() == fichaActual.getFigura()
                    || matrizFichas[fila][i].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[fila][i]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;

        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public int validarFiguraHaciaLaDerecha(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = columna + 1; matrizFichas[fila][i] != null && i < matrizFichas.length - 1; i++) {
            if (matrizFichas[fila][i].getColor() != fichaActual.getColor()
                    || matrizFichas[fila][i].getFigura() == fichaActual.getFigura()
                    || matrizFichas[fila][i].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[fila][i]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public int validarFiguraHaciaArriba(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = fila - 1; matrizFichas[i][columna] != null && i > 0; i--) {
            if (matrizFichas[i][columna].getColor() != fichaActual.getColor()
                    || matrizFichas[i][columna].getFigura() == fichaActual.getFigura()
                    || matrizFichas[i][columna].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[i][columna]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public int validarFiguraHaciaAbajo(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = fila + 1; matrizFichas[i][columna] != null && i < matrizFichas.length - 1; i++) {
            if (matrizFichas[i][columna].getColor() != fichaActual.getColor()
                    || matrizFichas[i][columna].getFigura() == fichaActual.getFigura()
                    || matrizFichas[i][columna].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[i][columna]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos;
    }

    public int validarColorHaciaLaIzquierda(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = columna - 1; matrizFichas[fila][i] != null && i > 0; i--) {
            if (matrizFichas[fila][i].getColor() == fichaActual.getColor()
                    || matrizFichas[fila][i].getFigura() != fichaActual.getFigura()
                    || matrizFichas[fila][i].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[fila][i]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        //System.out.println("cantidad de puntos por la izquierda"+puntos);
        return puntos + 1;
    }

    public int validarColorHaciaLaDerecha(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = columna + 1; matrizFichas[fila][i] != null && i < matrizFichas.length - 1; i++) {
            if (matrizFichas[fila][i].getColor() == fichaActual.getColor()
                    || matrizFichas[fila][i].getFigura() != fichaActual.getFigura()
                    || matrizFichas[fila][i].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[fila][i]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public int validarColorHaciaArriba(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = fila - 1; matrizFichas[i][columna] != null && i > 0; i--) {
            if (matrizFichas[i][columna].getColor() == fichaActual.getColor()
                    || matrizFichas[i][columna].getFigura() != fichaActual.getFigura()
                    || matrizFichas[i][columna].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[i][columna]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public int validarColorHaciaAbajo(Ficha fichaActual, int fila, int columna, ArrayList<Ficha> subconjunto) {
        int puntos = 0;
        boolean estaEnSubconjunto = false;
        for (int i = fila + 1; matrizFichas[i][columna] != null && i < matrizFichas.length - 1; i++) {
            if (matrizFichas[i][columna].getColor() == fichaActual.getColor()
                    || matrizFichas[i][columna].getFigura() != fichaActual.getFigura()
                    || matrizFichas[i][columna].getTipo() == fichaActual.getTipo()) {
                return -1;
            }
            if (estaEnLaSubconjunto(subconjunto, matrizFichas[i][columna]) == true) {
                estaEnSubconjunto = true;
            }
            puntos++;
        }
        if (estaEnSubconjunto) {
            return 1;
        }
        return puntos + 1;
    }

    public boolean estaEnLaSubconjunto(ArrayList<Ficha> subconjunto, Ficha ficha) {

        return subconjunto.contains(ficha);
    }
    
    public void permutarFichas(ArrayList<Ficha> conjunto, int tamano, ArrayList<ArrayList<Ficha>> todas) {
        if (0 == tamano) {
            ArrayList<Ficha> nuevo = (ArrayList<Ficha>) conjunto.clone();
            todas.add(nuevo);
        } else {
            for (int i = 0; i < tamano; i++) {
                Collections.swap(conjunto, tamano - 1, i);
                permutarFichas(conjunto, tamano - 1, todas);
                Collections.swap(conjunto, tamano - 1, i);
            }
        }
    }

    private void eliminarNull(ArrayList<ArrayList<Ficha>> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.get(i).size(); j++) {
                if (lista.get(i).get(j) == null) {
                    lista.remove(lista.get(i));
                    break;
                }
            }
        }
    }

    //retorna todas las combinaciones que comparten una propiedad (figuro/color)
    private ArrayList<ArrayList<Ficha>> combinaciones(Ficha[] mano) {
        ArrayList<ArrayList<Ficha>> lista = new ArrayList();
        //setea en lista todos los subconjuntos de mano
        partesDe(lista, new ArrayList(), mano, 0);
        ArrayList<ArrayList<Ficha>> remover = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            ArrayList<Ficha> sub = lista.get(i);//toma un subconjunto
            //si no comparte el color de todo el conjunto 
            //y si no tiene fichas repetidas (mismo color y figura)
            //lo agrega a remover
            if (!coincideColor(sub) && !noRepetidas(sub)) {
                remover.add(sub);
                break;
            }
            //lo mismo que el if anterior pero con figura
            else if (!coincideFigura(sub) && !noRepetidas(sub)) {
                remover.add(sub);
                break;
            }
        }
        //los elimina de lista
        for (int i = 0; i < remover.size(); i++) {
            lista.remove(remover.get(i));
        }
        //elimina cualquier subcinjunto que pudiese no tener fichas
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).size() == 0) {
                lista.remove(lista.get(i));
            }
        }
        return lista;
    }

    private boolean coincideColor(ArrayList<Ficha> sub) {

        for (int i = 1; i < sub.size(); i++) {
            if (!sub.get(i - 1).getColor().equals(sub.get(i).getColor())) {
                return false;
            }
        }
        return true;
    }

    private boolean noRepetidas(ArrayList<Ficha> sub) {
        for (int i = 1; i < sub.size(); i++) {
            for (int j = i + 1; j < sub.size(); j++) {
                if (sub.get(i).getTipo().equals(sub.get(j).getTipo())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean coincideFigura(ArrayList<Ficha> sub) {

        for (int i = 1; i < sub.size(); i++) {
            if (!sub.get(i - 1).getFigura().equals(sub.get(i).getFigura())) {
                return false;
            }
        }
        return true;
    }

    //https://java2blog.com/find-subsets-set-power-set/
    //retorna todos los subconjuntos
    private void partesDe(ArrayList<ArrayList<Ficha>> lista, ArrayList<Ficha> resultado, Ficha[] mano, int inic) {
        lista.add(new ArrayList(resultado));
        for (int i = inic; i < mano.length; i++) {
            if (mano[i] != null) {
                resultado.add(mano[i]);
                partesDe(lista, resultado, mano, i + 1);
                resultado.remove(resultado.size() - 1);
            }

        }
    }

    /*
    public Jugada getMejorJugada() {
        ArrayList<Jugada> jugadas = getTodasLasJugadas();
        System.out.println("tamaño de todas las jugadas " + jugadas.size());
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
        System.out.println("********JUGADA CON MAS PUNTOS*********");
        for (Movimiento movimiento : mayor.getMovimientos()) {
            System.out.println(movimiento.getFicha().getTipo() + " fila : " + movimiento.getFila() + " col : "
                    + movimiento.getColumna() + " p : " + movimiento.getPuntos());
        }
        System.out.println("Total de puntos: " + mayor.getPuntajeTotal());
        System.out.println("**************************************");
        return mayor;
    }*/
    public ArrayList<Jugada> getTodasLasJugadas() {
        ArrayList<ArrayList<Movimiento>> arr = new ArrayList();
        ArrayList<Jugada> jugadas = new ArrayList();
        //obtengo todos los subconjuntos de posibles de la mano
        ArrayList<ArrayList<Ficha>> combinaciones = combinaciones(mano);

        //System.out.println("Tam com : " + combinaciones.size());
        //la mano llega bien
        for (int i = 0; i < combinaciones.size(); i++) {
            //permuto cada combinacion
            ArrayList<ArrayList<Ficha>> permutacionesDe = new ArrayList();
            permutarFichas(combinaciones.get(i), combinaciones.get(i).size(), permutacionesDe);
            //ahora recorro cada una de las permutaciones
            //System.out.println("Tamano permutaciones: " + permutacionesDe.size());
            for (int j = 0; j < permutacionesDe.size(); j++) {
                /*
                System.out.println("Tam per indi : " +permutacionesDe.get(j).size() );
                for (int k = 0; k < permutacionesDe.get(j).size(); k++) {
                    System.out.println(permutacionesDe.get(j).get(k).getTipo());
                }
                System.out.println("");
                 */
                if (permutacionesDe.get(j) != null) {
                    //revisar porque entra null
                    ArrayList<Jugada> jug = getListaJugadas(permutacionesDe.get(j));
                    jugadas.addAll(jug);
                }
            }

        }
        /*for (Jugada jugada : jugadas) {
            System.out.println("TAMANO DE LA JUGADA **>>>" + jugada.getMovimientos().size());
            for (Movimiento movimiento : jugada.getMovimientos()) {
                System.out.println(movimiento.getFicha().getTipo() + " fila : " + movimiento.getFila() + " col : "
                        + movimiento.getColumna() + " p : " + movimiento.getPuntos());
            }
            System.out.println("TOTAL DE PUNTOS **>>>" + jugada.puntajeTotal);
        }*/
        return jugadas;
    }

    public void eliminarFichas(ArrayList<Movimiento> movimientos) {
        for (int i = 0; i < mano.length; i++) {
            for (int j = 0; j < movimientos.size(); j++) {
                if (movimientos.get(j).getFicha().equals(mano[i])) {
                    mano[i] = null;
                    break;
                }
            }
        }
    }

    public void cambiarMano() {
        //recorro las 6 fichas de la mano
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] != null) {
                this.fichas.add(mano[i]);//meto cada ficha en "bolsa"
                mano[i] = fichas.get(0);//saco la ficha
                fichas.remove(mano[i]);//la elimino de la "bolsa" 
            }

        }

    }

    public int getTamMano() {
        int num = 0;
        for (int i = 0; i < mano.length; i++) {
            if (mano[i] != null) {
                num++;
            }
        }
        return num;
    }

    public Jugada getMejorJugada() {
        ArrayList<Jugada> jugadas = getTodasLasJugadas();
        //System.out.println("tamaño de todas las jugadas " + jugadas.size());
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
        return mayor;
    }

}
