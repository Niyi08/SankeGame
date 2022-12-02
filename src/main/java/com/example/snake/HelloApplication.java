package com.example.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class HelloApplication extends Application {
    private static Random RAND = new Random();
    static final Image PLAYER_IMAGE = new Image("file:src/main/resources/img/jugador.png");
    private GraphicsContext gc;

    Jugador jugador;
    List<Elemento> Obstaculos;
    private double mouseX;
    private int puntaje;

    /**
     * Esta funcion se encarga de configurar la ventana y el escenario del juego
     * @param stage El escenario o ventana a configurar
     **/
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        //Ejecuta la funcion run() cada 10 milisegundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //Asigna a la variable mouseX el valor de la posicion del Mouse en el eje horizontal
        canvas.setOnMouseMoved(e -> mouseX = e.getX());

        setup();
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Mr. Sn4k3");
        stage.show();
    }

    /**
     * Esta funcion se encarga de configurar los valores iniciales del juego
     * 
     **/
    
    private void setup(){
        jugador = new Jugador(Constantes.WIDTH / 2, 60,PLAYER_IMAGE);
        Obstaculos = new ArrayList<>();
        puntaje = 0;
    }

    private int contador = 0;
    private int contador2 = 0;
    private int colisiones = 0;

    /**
     * Esta funcion renderiza cada frame
     * @param gc El contexto de graficos que permite "dibujar" en la ventana
     **/
    private void run(GraphicsContext gc){
        //Aqui se dibuja el fondo del juego
        gc.setFill(Color.rgb(170,215,81));
        gc.fillRect(0,0,Constantes.WIDTH,Constantes.HEIGHT+900);

        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,15));
        gc.setFill(Color.WHITE);
        gc.fillText("Puntaje: "+puntaje,15,25);

        //Aca se dibuja al jugador y se actualiza su posicion en X tomando la posicion del mouse.
        jugador.draw(gc);
        jugador.posX = (int) mouseX;

        if (contador == 0 && contador2 == 0){
            //Se crea un array de obstaculos si ambos contadores estan en 0
            IntStream.range(0,RAND.nextInt(1,4)).mapToObj(i -> this.newObstaculo()).forEach(Obstaculos::add);
        }

        //Este contador funciona como "delay" para los obstaculos. Se resetea despues de cada 100 frames
        contador++;
        if (contador == 100){
            contador = 0;
        }

        //Este contador sirve para bajar la velocidad en caso de colision durante 100 frames.
        if(contador2>0){
            contador2++;
        }
        if (contador2 == 100){
            contador2 = 0;
        }

        int speed = 2;


        for(int i=0;i<Obstaculos.size();i++){
            //Se muestran todos los elementos del array de obstaculos en el juego
            Obstaculos.get(i).draw(gc);

            //En caso de colision con obstaculo
            if (Obstaculos.get(i).colision(jugador)){
                colisiones++;
                contador2++;
                System.out.println(colisiones);
                if (contador2 > 0){
                    //Se elimina el obstaculo de la colision para que no cuente mas de una colision
                    Obstaculos.remove(i);
                }
            }else{
                if(contador2 == 0){
                    //Velocidad normal
                    Obstaculos.get(i).update(speed);
                }else{
                    //Velocidad lenta en caso de COLISION
                    Obstaculos.get(i).update(speed/2);
                }
            }
        }
    }

    //Se crea un objeto de tipo Obstaculo en una posicion random de X
    Obstaculo newObstaculo(){
        return new Obstaculo(35, 55, RAND.nextInt(0,Constantes.WIDTH),RAND.nextInt(Constantes.HEIGHT-20, Constantes.HEIGHT+60));
    }

    public static void main(String[] args) {
        launch();
    }



}