package com.example.snake;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Clase que genera una pregunta matematica.
 *
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/
public class Obstaculo extends Elemento {

    /**
     * Lista de las rutas de imagenes de fruta.
     **/
    String imgList[] = {
            "/img/1.png",
            "/img/2.png",
            "/img/3.png",
    };

    String seleccion = imgList[Constantes.RAND.nextInt(0,3)];

    /**
     * Imagen de obstaculo al azar.
     **/
    Image img = new Image(getClass().getResourceAsStream(seleccion));
    int tammX, tammY;
    public Obstaculo(int tamX, int tamY, int posX, int posY) {
        super(tamX, tamY, posX, posY);
    }

    /**
     * Esta funcion se encarga de dibujar un obstaculo en el juego.
     * @param gc El contexto graficó con el que se dibuja.
     **/
    @Override
    public void draw(GraphicsContext gc){
//        try{
            tammX = getTamanoX();
            tammY = getTamanoY();
            if(getPosY() > 0){
                if (seleccion.contains("4")) {
                    tammY = getTamanoY()-10;
                    tammX = getTamanoX();
                }else if(seleccion.contains("3")){
                    tammX = getTamanoX() + 20;
                    tammY = getTamanoY();
                }else{
                    tammY = getTamanoY();
                    tammX = getTamanoX();
                }
                gc.drawImage(img,getPosX(),getPosY(),tammX,tammY);

//                System.out.println("holis");

            }
//
//        }catch (Exception e){
//            System.out.println("Error " + e);
//        }

    }


}
