package com.example.snake;

import javafx.scene.canvas.GraphicsContext;

/**
 * Clase que se encarga de crear obstaculos y frutas.
 * 
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/
public abstract class Elemento {
    private int tamanoX;
    private int tamanoY;
    private int posX;
    private int posY;

    public int getTamanoX() {
        return tamanoX;
    }
    public int getTamanoY() {
        return tamanoY;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }


    public void setTamanoX(int tamanoX) {
        this.tamanoX = tamanoX;
    }
    public void setTamanoY(int tamanoY) {
        this.tamanoY = tamanoY;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Elemento(int tamanoX, int tamanoY, int posX, int posY){
        super();
        this.tamanoX = tamanoX;
        this.tamanoY = tamanoY;
        this.posX = posX;
        this.posY = posY;
    }

    /**
    * Esta funcion hace que el elemento suba n pixeles cada frame
    * 
    **/
    public void update(int speed){

        if(getPosY()>0){
            this.posY -= speed;
        }
    }
    
    /**
    * Esta funcion calcula la colision entre el Elemento y el jugador
    * @return d distancia en pixeles entre el Elemento y el jugador
    **/
    public boolean colision(Jugador jugador){
        int d = distancia(getPosX() + getTamanoX() / 2, getPosY() + getTamanoY()/ 2, jugador.posX + jugador.tamX /2, jugador.posY + jugador.tamY);
        return d < jugador.tamX / 2 + this.getTamanoX() / 2;
    }

    /**
    * Esta funcion calcula la distancia entre dos puntos
    * @return d La distancia en entre dos puntos
    **/
    private int distancia(int x1,int y1,int x2,int y2){
        int d = (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
//        System.out.println(d);
        return d;

    }

    public abstract void draw(GraphicsContext gc);
}
