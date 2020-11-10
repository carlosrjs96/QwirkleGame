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
public enum FichaType {
    AMARILLOCIRCULO(".\\src\\Imagenes\\y1.jpg",FiguraType.CIRCULO,ColorType.AMARILLO),
    AMARILLOCUADRADO(".\\src\\Imagenes\\y2.jpg",FiguraType.CUADRADO,ColorType.AMARILLO),
    AMARILLOROMBO(".\\src\\Imagenes\\y3.jpg",FiguraType.ROMBO,ColorType.AMARILLO),
    AMARILLOESTRELLA(".\\src\\Imagenes\\y4.jpg",FiguraType.ESTRELLA,ColorType.AMARILLO),
    AMARILLOPENTAGONO(".\\src\\Imagenes\\y5.jpg",FiguraType.PENTAGONO,ColorType.AMARILLO),
    AMARILLOTRIANGULO(".\\src\\Imagenes\\y6.jpg",FiguraType.TRIANGULO,ColorType.AMARILLO),
    
    VERDECIRCULO(".\\src\\Imagenes\\v1.jpg",FiguraType.CIRCULO,ColorType.VERDE),
    VERDECUADRADO(".\\src\\Imagenes\\v2.jpg",FiguraType.CUADRADO,ColorType.VERDE),
    VERDEROMBO(".\\src\\Imagenes\\v3.jpg",FiguraType.ROMBO,ColorType.VERDE),
    VERDEESTRELLA(".\\src\\Imagenes\\v4.jpg",FiguraType.ESTRELLA,ColorType.VERDE),
    VERDEPENTAGONO(".\\src\\Imagenes\\v5.jpg",FiguraType.PENTAGONO,ColorType.VERDE),
    VERDETRIANGULO(".\\src\\Imagenes\\v6.jpg",FiguraType.TRIANGULO,ColorType.VERDE),
    
    ROJOCIRCULO(".\\src\\Imagenes\\r1.jpg",FiguraType.CIRCULO,ColorType.ROJO),
    ROJOCUADRADO(".\\src\\Imagenes\\r2.jpg",FiguraType.CUADRADO,ColorType.ROJO),
    ROJOROMBO(".\\src\\Imagenes\\r3.jpg",FiguraType.ROMBO,ColorType.ROJO),
    ROJOESTRELLA(".\\src\\Imagenes\\r4.jpg",FiguraType.ESTRELLA,ColorType.ROJO),
    ROJOPENTAGONO(".\\src\\Imagenes\\r5.jpg",FiguraType.PENTAGONO,ColorType.ROJO),
    ROJOTRIANGULO(".\\src\\Imagenes\\r6.jpg",FiguraType.TRIANGULO,ColorType.ROJO),
    
    NARANJACIRCULO(".\\src\\Imagenes\\n1.jpg",FiguraType.CIRCULO,ColorType.NARANJA),
    NARANJACUADRADO(".\\src\\Imagenes\\n2.jpg",FiguraType.CUADRADO,ColorType.NARANJA),
    NARANJAROMBO(".\\src\\Imagenes\\n3.jpg",FiguraType.ROMBO,ColorType.NARANJA),
    NARANJAESTRELLA(".\\src\\Imagenes\\n4.jpg",FiguraType.ESTRELLA,ColorType.NARANJA),
    NARANJAPENTAGONO(".\\src\\Imagenes\\n5.jpg",FiguraType.PENTAGONO,ColorType.NARANJA),
    NARANJATRIANGULO(".\\src\\Imagenes\\n6.jpg",FiguraType.TRIANGULO,ColorType.NARANJA),
    
    MORADOCIRCULO(".\\src\\Imagenes\\m1.jpg",FiguraType.CIRCULO,ColorType.MORADO),
    MORADOCUADRADO(".\\src\\Imagenes\\m2.jpg",FiguraType.CUADRADO,ColorType.MORADO),
    MORADOROMBO(".\\src\\Imagenes\\m3.jpg",FiguraType.ROMBO,ColorType.MORADO),
    MORADOESTRELLA(".\\src\\Imagenes\\m4.jpg",FiguraType.ESTRELLA,ColorType.MORADO),
    MORADOPENTAGONO(".\\src\\Imagenes\\m5.jpg",FiguraType.PENTAGONO,ColorType.MORADO),
    MORADOTRIANGULO(".\\src\\Imagenes\\m6.jpg",FiguraType.TRIANGULO,ColorType.MORADO),
    
    AZULCIRCULO(".\\src\\Imagenes\\a1.jpg",FiguraType.CIRCULO,ColorType.AZUL),
    AZULCUADRADO(".\\src\\Imagenes\\a2.jpg",FiguraType.CUADRADO,ColorType.AZUL),
    AZULROMBO(".\\src\\Imagenes\\a3.jpg",FiguraType.ROMBO,ColorType.AZUL),
    AZULESTRELLA(".\\src\\Imagenes\\a4.jpg",FiguraType.ESTRELLA,ColorType.AZUL),
    AZULPENTAGONO(".\\src\\Imagenes\\a5.jpg",FiguraType.PENTAGONO,ColorType.AZUL),
    AZULTRIANGULO(".\\src\\Imagenes\\a6.jpg",FiguraType.TRIANGULO,ColorType.AZUL);
    
    
    private String image;
    private FiguraType figura; 
    private ColorType color;
    
    FichaType(String image, FiguraType figura, ColorType color){
        this.image = image;
        this.figura = figura;
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public FiguraType getFigura() {
        return figura;
    }

    public ColorType getColor() {
        return color;
    }
    
    
    
}
