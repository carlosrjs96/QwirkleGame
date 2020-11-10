/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Juego;
import Vista.PantallaMenu;
import Vista.PantallaTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jarod
 */
public class ControladorMenu implements ActionListener{
    
    private PantallaMenu pantallaMenu;
    private Juego juego;
    private ControladorTablero controladorTablero;

    public ControladorMenu( ) {
        this.pantallaMenu = new PantallaMenu();
        
        pantallaMenu.setVisible(true);
        _init_();
    }
    
    private void _init_(){
        pantallaMenu.btnAlgSimMin.addActionListener(this);
        pantallaMenu.btnAlgSimMax.addActionListener(this);
        pantallaMenu.btnAlgAvaMin.addActionListener(this);
        pantallaMenu.btnAlgAvaMax.addActionListener(this);
        pantallaMenu.btnPlayMin.addActionListener(this);
        pantallaMenu.btnPlaMax.addActionListener(this);
        pantallaMenu.btnJugar.addActionListener(this);
        pantallaMenu.btnSalir.addActionListener(this);
        
        
        
        
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pantallaMenu.isVisible()){
            if (e.getSource().equals(pantallaMenu.btnAlgSimMin)){
                int num = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                if (num>0){
                    pantallaMenu.lblAlgSim.setText(Integer.toString(num-1));
                    pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())+1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgSimMax)){
                int num = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                if (0<Integer.parseInt(pantallaMenu.lblTotal.getText())){
                   pantallaMenu.lblAlgSim.setText(Integer.toString(num+1)); 
                   pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgAvaMin)){
                int num = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                if (num>0){
                    pantallaMenu.lblAlgAva.setText(Integer.toString(num-1));
                    pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())+1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnAlgAvaMax)){
                int num = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                if (0<Integer.parseInt(pantallaMenu.lblTotal.getText())){
                    pantallaMenu.lblAlgAva.setText(Integer.toString(num+1));
                    pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnPlayMin)){
                int num = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (num>0){
                    pantallaMenu.lblPla.setText(Integer.toString(num-1));
                    pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())+1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnPlaMax)){
                int num = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (0<Integer.parseInt(pantallaMenu.lblTotal.getText())){
                    pantallaMenu.lblPla.setText(Integer.toString(num+1));
                    pantallaMenu.lblTotal.setText(Integer.toString(Integer.parseInt(pantallaMenu.lblTotal.getText())-1));
                }
            }
            else if (e.getSource().equals(pantallaMenu.btnJugar)){
                //validar que haya al menos un jugador de cada tipo
                int numSim = Integer.parseInt(pantallaMenu.lblAlgSim.getText());
                int numAva = Integer.parseInt(pantallaMenu.lblAlgAva.getText());
                int numHum = Integer.parseInt(pantallaMenu.lblPla.getText());
                if (numSim>0 && numAva >0) {
                    int n = Integer.parseInt(pantallaMenu.txtTamano.getText());
                    this.juego = new Juego (numSim, numAva,n);
                    //cerrar esta pantalla 
                    pantallaMenu.dispose();
                    //abrir la del tablero
                    
                    this.controladorTablero = new ControladorTablero(juego, this, n);
                }
                
            }
            else if (e.getSource().equals(pantallaMenu.btnSalir)){
                pantallaMenu.dispose();
            }
        }
        
    }
    
}
