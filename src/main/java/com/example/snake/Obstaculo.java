package com.example.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Obstaculo extends Elemento {
    //int SPEED = 20;
    Image img = new Image("file:src/main/resources/img/1.png");
    public Obstaculo(int tamX, int tamY, int posX, int posY) {
        super(tamX, tamY, posX, posY);
    }

    @Override
    public void draw(GraphicsContext gc){
        try{
            if(getPosY() > 0){

                gc.drawImage(img,getPosX(),getPosY(),getTamanoX(),getTamanoY());

//                System.out.println("holis");

            }

        }catch (Exception e){
            System.out.println("Error " + e);
        }

    }


}
