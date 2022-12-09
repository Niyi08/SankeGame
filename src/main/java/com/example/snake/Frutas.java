package com.example.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Clase que dibuja una fruta con una respuesta
 * 
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/
public class Frutas extends Elemento {

    /**
     * Lista de las rutas de imagenes de fruta.
     **/
    String imgList[] = {
            "/img/F1.png",
            "/img/F2.png",
            "/img/F3.png",
            "/img/F4.png",
    };

    /**
     * Imagen de fruta al azar.
     **/
    Image img = new Image(getClass().getResourceAsStream(imgList[Constantes.RAND.nextInt(0,4)]));

    public Frutas(int tamX, int tamY, int posX, int posY) {
        super(tamX, tamY, posX, posY);
    }


    /**
     * Esta funcion se encarga de dibujar una fruta en el juego con su número de respuesta.
     * @param gc El contexto graficó con el que se dibuja.
     **/
    @Override
    public void draw(GraphicsContext gc){
//        try{
            if(getPosY() > 0){
                gc.drawImage(img,getPosX(),getPosY(),getTamanoX(),getTamanoY());
                gc.setFill(Color.WHITE);
                gc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                gc.fillText(String.valueOf(this.getNumero()), getPosX()+getTamanoX()/2,getPosY()+getTamanoY()/1.35);

            }

//        }catch (Exception e){
//            System.out.println("Error " + e);
//        }

    }
}
