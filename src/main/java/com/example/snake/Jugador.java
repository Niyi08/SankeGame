package com.example.snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Clase que genera una pregunta matematica.
 *
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/
public class Jugador {

    int tamX = 33;
    int tamY = 70;
    int posX, posY;

    /**
     * La imagen de la serpiente
     **/
    Image img;

    public Jugador(int posX, int posY, Image img){
        this.posX = posX;
        this.posY = posY;
        this.img = img;
    }


    /**
     * Esta funcion dibuja al jugador en le juego
     * @param gc El contexto graficó con el que se dibuja.
     * @param col Usado para mostrar a la serpiente normal o con la boca abierta.
     **/
    public void draw(GraphicsContext gc, boolean col){
        if(col){
            this.img = new Image(getClass().getResourceAsStream("/img/jugadorA.png"));
        }else{
            this.img = new Image(getClass().getResourceAsStream("/img/jugador.png"));
        }
        gc.drawImage(img,posX,posY,tamX,tamY);
    }

}
