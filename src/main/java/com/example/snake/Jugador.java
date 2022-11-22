package com.example.snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//Clase que se encarga de crear al jugador.
public class Jugador {

    int tamX = 33;
    int tamY = 80;
    int posX, posY;
    Image img;

    public Jugador(int posX, int posY, Image img){
        this.posX = posX;
        this.posY = posY;
        this.img = img;
    }

//    public void update(){
//
//    }

    public void draw(GraphicsContext gc){
        gc.drawImage(img,posX,posY,tamX,tamY);
    }

}
