/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Juego;
import Modelo.Jugador;
import Vista.PantallaTablero;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Jarod
 */
public class ControladorTablero implements ActionListener {

    private PantallaTablero pantallaTablero;
    private Juego juego;

    public ControladorTablero(Juego juego, ControladorMenu controladorMenu, int n) {
        this.juego = juego;
        this.pantallaTablero = new PantallaTablero();
        this.pantallaTablero.setN(n);
        this.pantallaTablero._init_(6, juego.getJugadores().size());
        this.pantallaTablero.setVisible(true);
        _init_();
        update();
    }

    public ControladorTablero(Juego juego, int n) {
        this.juego = juego;
        this.pantallaTablero = new PantallaTablero();
        this.pantallaTablero.setN(n);
        this.pantallaTablero._init_(6, juego.getJugadores().size());
        this.pantallaTablero.setVisible(true);
        _init_();
        update();
    }

    private void _init_() {

        pantallaTablero.btnSalir.addActionListener(this);
        pantallaTablero.btnSiguiente.addActionListener(this);

        for (int i = 0; i < pantallaTablero.butArray.length; i++) {
            for (int j = 0; j < pantallaTablero.butArray[i].length; j++) {
                pantallaTablero.butArray[i][j].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //else para la pantalla del juego
        if (pantallaTablero.isActive()) {
            if (e.getSource().equals(pantallaTablero.btnSiguiente)) {
                int veces = 1;
                for (int i = 0; i < veces; i++) {
                    //System.out.println("Bytes Total: "+Runtime.getRuntime().totalMemory());
                    double inicio = System.currentTimeMillis();

                    if (juego.jugar()) {
                        update();
                        Component frame = null;

                        JOptionPane.showMessageDialog(frame, "Ha ganado el Jugador " + Integer.toString(juego.getGanador()), "Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Felicidades");
                    } else {
                        update();
                    }
                    double fin = System.currentTimeMillis();
                    double tiempo = ((fin - inicio));

                    
                }
                

            } else if (e.getSource().equals(pantallaTablero.btnSalir)) {
                pantallaTablero.dispose();

            } else {
                for (int i = 0; i < pantallaTablero.butArray.length; i++) {
                    for (int j = 0; j < pantallaTablero.butArray[i].length; j++) {
                        if (e.getSource().equals(pantallaTablero.butArray[i][j])) {
                            //no lo usamos pero por si acaso

                        }
                    }
                }
            }

        }
    }

    private void update() {
        setImagenes();
        puntajes();
    }

    private void puntajes() {
        String str = "<html>Turno : " + Integer.toString(juego.getTurno() + 1) + "<br/>";
        str += "Juega : Jugador " + Integer.toString((juego.getTurno() % juego.getJugadores().size()) + 1) + "<br/>";
        for (int i = 0; i < juego.getJugadores().size(); i++) {
            str += "Jugador " + Integer.toString(i + 1) + " : " + Float.toString((float) juego.getJugadores().get(i).getPuntaje());

            str += "    (" + juego.getJugadores().get(i).getPuntajeUltima() + " pts)";
            str += " / Mano : " + Integer.toString(juego.getJugadores().get(i).getTamMano());
            str += " / Bolsa : " + Integer.toString(juego.getJugadores().get(i).getFichas().size());
            str += " / # : " +Integer.toString(juego.getJugadores().get(i).getFichasUsadas());

            str += "<br/>";

        }
        str += "<html/>";

        pantallaTablero.lblTurno.setText(str);
    }

    private void setImagenes() {
        setTablero();
        setManos();
    }

    private void setTablero() {

        int height = pantallaTablero.butArray[0][0].getHeight();
        int width = pantallaTablero.butArray[0][0].getWidth();

        for (int i = 0; i < juego.getMatrizFichas().length; i++) {
            for (int j = 0; j < juego.getMatrizFichas()[i].length; j++) {
                if (juego.getMatrizFichas()[i][j] != null) {

                    JButton b = pantallaTablero.butArray[i][j];
                    ImageIcon ima = new ImageIcon(juego.getMatrizFichas()[i][j].getImagen());
                    Image im = ima.getImage();
                    Image newI = im.getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH);

                    ima = new ImageIcon(newI);  // transform it back
                    b.setIcon(ima);
                    b.setOpaque(false);
                    b.setContentAreaFilled(false);
                    b.setBorderPainted(false);
                }
            }
        }
    }

    private void setManos() {
        int height = pantallaTablero.butArray[0][0].getHeight();
        int width = pantallaTablero.butArray[0][0].getWidth();
        for (int i = 0; i < pantallaTablero.fichasJugadores.length; i++) {
            Jugador jugador = juego.getJugadores().get(i);
            for (int j = 0; j < pantallaTablero.fichasJugadores[i].length; j++) {
                if (jugador.getMano()[j] != null) {
                    //setea imagen
                    JButton b = pantallaTablero.fichasJugadores[i][j];

                    ImageIcon ima = new ImageIcon(jugador.getMano()[j].getImagen());
                    Image im = ima.getImage();
                    Image newI = im.getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH);

                    ima = new ImageIcon(newI);  // transform it back

                    b.setIcon(ima);

                    b.setOpaque(false);
                    b.setContentAreaFilled(false);
                    b.setBorderPainted(false);

                } else {
                    JButton b = pantallaTablero.fichasJugadores[i][j];
                    b.setIcon(null);
                }
            }
        }
    }

}
