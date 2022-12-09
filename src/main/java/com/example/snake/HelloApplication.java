package com.example.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.ImageCursor;
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
import java.util.stream.IntStream;
import java.util.Random;

/**
 * Clase principal con la logica del juego.
 *
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/
public class HelloApplication extends Application {
    private static Random RAND = Constantes.RAND;


    Jugador jugador;
    List<Elemento> Obstaculos;
    List<Elemento> Frutas;

    /**
     * Variable que contiene el valor del mouse en el eje X.
     **/
    private double mouseX;

    /**
     * Variable que contiene el valor del puntaje en el juego.
     **/
    private int puntaje;

    /**
     * Variable que contiene el valor de si el jugador perdio todas sus vidas.
     **/
    private boolean gameOver = false;

    /**
     * Variable que contiene el valor de si la fruta comida tenia la respuesta correcta.
     **/
    private boolean resuelta = false;

    /**
     * Variable que contiene el valor de si el juego ya inicio o no.
     **/
    private boolean inicio = false;

    /**
     * Esta funcion se encarga de configurar la ventana y el escenario del juego
     * @param stage El escenario o ventana a configurar
     **/
    @Override
    public void start(Stage stage) throws IOException {
        final Image CURSOR= new Image(getClass().getResourceAsStream("/img/cursorB.png"));
        gameOver = true;
        GraphicsContext gc;

        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        //Ejecuta la funcion run() cada 10 milisegundos
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(new ImageCursor(CURSOR));
        //Asigna a la variable mouseX el valor de la posicion del Mouse en el eje horizontal
        canvas.setOnMouseMoved(e -> {
            if(e.getX() < Constantes.WIDTH -33) {
                mouseX = e.getX();
            }
        });
        canvas.setOnMouseClicked(e -> {
            if(!inicio){
                inicio = true;
                setup();
            }
            if(gameOver) {
                gameOver = false;
                setup();
            }
        });

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
        final Image PLAYER_IMAGE = new Image(getClass().getResourceAsStream("/img/jugador.png"));
        jugador = new Jugador(Constantes.WIDTH / 2, 60,PLAYER_IMAGE);
        Obstaculos = new ArrayList<>();
        Frutas = new ArrayList<>();
        puntaje = 0;
        speed = 2;

    }

    /**
     * Contador que funciona como "delay" para enviar un array de obstaculos y uno de frutas.
     **/
    private int contador = 0;

    /**
     * Contador que sirve para bajar la velocidad en caso de COLISION con un obstaculo.
     **/
    private int contador2 = 0;

    /**
     * Contador que sirve que la serpiente abra la boca al comer una fruta.
     **/
    private int contador3 = 0;

    /**
     * Contador de las colisiones con un obstaculo.
     **/
    private int colisiones = 0;

    int posXCursorM=70;
    boolean imgCursorM= true;
    String operacion = GenerarPregunta.gen();

    /**
     * Velocidad a la que se desplazan los elementos. Píxeles por frame.
     **/
    int speed = 2;

    /**
     * Esta funcion renderiza cada frame
     * @param gc El contexto de gráficos que permite "dibujar" en la ventana
     **/
    private void run(GraphicsContext gc){

        /**
         * La imagen del cursor
         **/
        final Image CURSOR= new Image(getClass().getResourceAsStream("/img/cursorB.png"));

        /**
         * La imagen de los corazones que representan las vidas del jugador
         **/
        final Image VIDAS = new Image(getClass().getResourceAsStream("/img/heart.png"));

        //Si el puntaje es menor a 30 la velocidad es 2
        if(puntaje<30){
            speed=2;
            if (contador >= 100) {
                contador = 0;
            }

        }else if(puntaje>=30 && puntaje<60){
            speed=3;
            //Si el puntaje es mayor o igual a 30 la velocidad es 3
            if (contador >= 80) {
                contador = 0;
            }

        }else if(puntaje>=60 && puntaje<100){
            speed=4;
            //Si el puntaje es mayor o igual a 60 la velocidad es 4
            if (contador >= 60) {
                contador = 0;
            }

        }else if(puntaje>=100){
            speed=5;
            //Si el puntaje es mayor o igual a 100 la velocidad es 5
            if (contador >= 50) {
                contador = 0;
            }

        }

        //Si el juego ya inicio y no se no han perdido las 3 vidas, se ejecuta el juego normalmente.
        if(!gameOver && inicio) {
            //Aqui se dibuja el fondo del juego
            gc.setFill(Color.rgb(170, 215, 81));
            gc.fillRect(0, 0, Constantes.WIDTH, Constantes.HEIGHT + 900);

            /**
             * Variable que sirve para mostrar el estado la serpiente. Falso, la serpiente está normal. Verdadero, la serpiente tiene la boca abierta.
             **/
            boolean col = false;

            //Se actualiza la posicion del jugador de acuerdo a la posicion del mouse en X
            jugador.posX = (int) mouseX;


            if (contador == 0 && contador2 == 0) {
                //Se crea un array de obstaculos si ambos contadores estan en 0
                IntStream.range(0, RAND.nextInt(1, 5)).mapToObj(i -> this.newObstaculo()).forEach(Obstaculos::add);
                //Se crea un array de frutas si ambos contadores estan en 0
                IntStream.range(0, RAND.nextInt(1, 3)).mapToObj(i -> this.newFruta()).forEach(Frutas::add);
            }

            contador++;

            //Este contador sirve para bajar la velocidad en caso de colision durante 100 frames.
            if (contador2 > 0) {
                contador2++;
            }
            if (contador2 == 100) {
                contador2 = 0;
            }

            //Este contador sirve para mostrar la imagen de la serpiente con la boca abierta durante 20 frames.
            if (contador3 > 0) {
                contador3++;
            }
            if (contador3 == 20) {
                contador3 = 0;
            }

            if(Obstaculos.size()>0) {
                for (int i = 0; i < Obstaculos.size(); i++) {
                    if(i<Obstaculos.size()){
                        //Se muestran todos los elementos del array de obstaculos en el juego
                        Obstaculos.get(i).draw(gc);
                        if (Obstaculos.size() >= 20) {
                            //Se elimina la fruta del juego cuando el array de obstaculos tiene 20 o mas elementos
                            Obstaculos.remove(i);
                        }

                        //En caso de colision con obstaculo
                        if (Obstaculos.get(i).colision(jugador)) {
                            colisiones++;
                            contador2++;
                            System.out.println(colisiones);
                            if (contador2 > 0) {
                                //Se elimina el obstaculo de la colision para que no cuente mas de una colision
                                Obstaculos.remove(i);
                            }
                        } else {
                            if (contador2 == 0) {
                                //Velocidad normal
                                Obstaculos.get(i).update(speed);

                            } else {
                                //Velocidad lenta en caso de COLISION
                                Obstaculos.get(i).update(speed / 2);

                            }
                        }

                    }
                }


            }

            if(Frutas.size()>0) {
                for (int i = 0; i < Frutas.size(); i++) {
                    if (i < Frutas.size()) {
//                        System.out.println("Siii");
                    //Se muestran todos los elementos del array de frutas en el juego
                    Frutas.get(i).draw(gc);
                    if (Frutas.size() >= 20) {
                        //Se elimina la fruta del juego cuando el array de frutas tiene 20 o mas elementos
                        Frutas.remove(i);
                    }


                    if (Frutas.get(i).colision(jugador)) {
                        contador3++;
                        Integer numeroFruta = Frutas.get(i).getNumero();
                        Integer respuesta = obtenerRespuesta(operacion);

                        //Se elimina la fruta del juego al ser comida.
                        Frutas.remove(i);

                        if (numeroFruta == respuesta) {
                            //Si la fruta comida contiene la respuesta correcta a la pregunta, se agregan 10 puntos.
                            puntaje = puntaje + 10;
                            resuelta = true;
                        } else {
                            if (puntaje > 0) {
                                //Si la fruta comida contiene una respuesta equivocada a la pregunta, se restan 10 puntos.
                                puntaje = puntaje - 10;
                            }
                        }
                    }
                    if (contador2 == 0) {
                        //Velocidad normal
                        Frutas.get(i).update(speed);


                    } else {
                        //Velocidad lenta en caso de COLISION
                        Frutas.get(i).update(speed / 2);
                    }

                    if (contador3 != 0) {
                        col = true;
                    } else {
                        col = false;
                    }

                    }
                }
            }
            jugador.draw(gc, col);

            //El juego termina al colisionar 3 veces.
            if (colisiones == 3) {
                gameOver = true;
            }


            //Texto con el puntaje
            gc.setFill(Color.rgb(148, 189, 70));
            gc.fillRect(0, 0, Constantes.WIDTH, 45);
            gc.setTextAlign(TextAlignment.LEFT);
            gc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
            gc.setFill(Color.WHITE);
            gc.fillText("Puntaje: " + puntaje, 15, 26);

            //Texto con la operacion matematicas
            if(resuelta){
                operacion = GenerarPregunta.gen();
                resuelta = false;
            }else{
                gc.setTextAlign(TextAlignment.LEFT);
                gc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22));
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setFill(Color.WHITE);
                gc.fillText(operacion, Constantes.WIDTH/2, 30);
            }


            //Vidas
            if(colisiones==0){
                gc.drawImage(VIDAS,Constantes.WIDTH-30, 15, 17, 17);
                gc.drawImage(VIDAS,Constantes.WIDTH-50, 15, 17, 17);
                gc.drawImage(VIDAS,Constantes.WIDTH-70, 15, 17, 17);
            } else if (colisiones==1) {
                gc.drawImage(VIDAS,Constantes.WIDTH-30, 15, 17, 17);
                gc.drawImage(VIDAS,Constantes.WIDTH-50, 15, 17, 17);
            } else if (colisiones==2) {
                gc.drawImage(VIDAS,Constantes.WIDTH-30, 15, 17, 17);
            }

        }else{
            //Texto de bienvenida que muestra la forma de control del juego.
            if(!inicio){
                gc.setFill(Color.rgb(148, 189, 70));
                gc.fillRect(0,0,Constantes.WIDTH,Constantes.HEIGHT);
                gc.setFont(Font.font(20));
                gc.setFill(Color.rgb(68, 113, 230));
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setFont(Font.font("verdana", FontWeight.BOLD, 35));
                gc.fillText("Mr. Sn4k3", Constantes.WIDTH/2, 50);
                gc.setFill(Color.BLACK);
                gc.setFont(Font.font("verdana", 25));
                gc.fillText("Mueve el cursor para \ncontrolar la serpiente", Constantes.WIDTH/2, Constantes.HEIGHT /2.8);
                gc.setFont(Font.font("verdana", 20));
                gc.fillText("Haz click para comenzar", Constantes.WIDTH/2, Constantes.HEIGHT /1.1);
                gc.fillText("Resuelve operaciones para \nganar puntos y evita los obstáculos", Constantes.WIDTH/2, Constantes.HEIGHT /1.4);

                //Esta seccion sirve para mover el cursor de derecha a izquierda en el texto de bienvenida
                if(posXCursorM==70){
                    imgCursorM=true;
                } else if (posXCursorM==Constantes.WIDTH-140) {
                    imgCursorM=false;
                }
                if(imgCursorM){
                    posXCursorM=posXCursorM+2;
                }else{
                    posXCursorM=posXCursorM-2;
                }

                gc.drawImage(CURSOR,posXCursorM, Constantes.HEIGHT /2.2);

            }else{
                //Texto que muestra el puntaje cuando el jugador pierde sus 3 vidas.
                gc.setFill(Color.rgb(50,50,50, 0.01));
                gc.fillRect(0,0,Constantes.WIDTH,Constantes.HEIGHT);
                gc.setFont(Font.font(20));
                gc.setFill(Color.RED);
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setFont(Font.font("verdana", 20));
                gc.fillText("Game Over \nTu puntaje es: " + puntaje + " \nClick para jugar de nuevo", Constantes.WIDTH/2, Constantes.HEIGHT /2.5);
                colisiones=0;
            }
        }
    }

    //Se crea un objeto de tipo Obstaculo en una posicion random de X
    Obstaculo newObstaculo(){
        return new Obstaculo(35, 55, RAND.nextInt(0,Constantes.WIDTH-35),RAND.nextInt(Constantes.HEIGHT-10, Constantes.HEIGHT+200));
    }

    //Se crea un objeto de tipo Fruta en una posicion random de X
    Frutas newFruta(){
        return new Frutas(40, 50, RAND.nextInt(0,Constantes.WIDTH-35),RAND.nextInt(Constantes.HEIGHT-10, Constantes.HEIGHT+200));
    }

    /**
     * Esta funcion se encarga de configurar los valores iniciales del juego
     * @param op Operacion matematica en forma de String
     * @return r El resultado de la operacion matematica
     **/
    private int obtenerRespuesta(String op){
        String spl[] = op.split(" ");

        if(spl[1].equals("+")){
            System.out.println("suma");
            return Integer.parseInt(spl[0]) + Integer.parseInt(spl[2]);
        } else if (spl[1].equals("-")) {
            System.out.println("resta");
            return Integer.parseInt(spl[0]) - Integer.parseInt(spl[2]);
        } else if (spl[1].equals("x")) {
            System.out.println("multi");
            return Integer.parseInt(spl[0]) * Integer.parseInt(spl[2]);
        }
        System.out.println(spl[1]);
        return -1;
    }

    public static void main(String[] args) {
        launch();
    }
}