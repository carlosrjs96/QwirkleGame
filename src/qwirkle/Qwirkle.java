/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;

import Controlador.ControladorMenu;
import Controlador.ControladorTablero;
import Modelo.Juego;

/**
 *
 * @author Carlos
 */
public class Qwirkle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //ControladorMenu controladorMenu = new ControladorMenu();
        
        
        int tam = 21;
        Juego juego = new Juego (2, 1, tam);
                    
                    
        ControladorTablero controladorTablero = new ControladorTablero(juego, tam);
        
        

    }
    
    
    
    
}
