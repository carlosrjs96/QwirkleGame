/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Carlos
 */
public class Ficha {
    private int valor;//es el valor que tiene la ficha al ponerlo en el tablero
    //acceso más rapido
    private String imagen;
    private FichaType tipo;
    private FiguraType figura;
    private ColorType color;
    private boolean pressed; 

  
    
    public Ficha(FichaType tipo) {
        this.valor = 0;
        this.tipo = tipo;
        this.imagen = tipo.getImage();
        this.color = tipo.getColor();
        this.figura = tipo.getFigura();
        this.pressed = false;
    }
    

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public FichaType getTipo() {
        return tipo;
    }

    public void setTipo(FichaType tipo) {
        this.tipo = tipo;
    }

    public FiguraType getFigura() {
        return figura;
    }

    public void setFigura(FiguraType figura) {
        this.figura = figura;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    
    
    
    
    
}
