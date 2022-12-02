package com.example.snake;

/**
 * Clase que genera una pregunta matematica
 * 
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/

public class GenerarPregunta {

    static String[] operaciones = {"SUMA", "RESTA", "MULTI"};
    public static int n1 = (int)(Math.random()*10+1);
    public static int n2 = (int)(Math.random()*10+1);
    public static String operacion = operaciones[(int)(Math.random()*3)];

    /**
    * Esta funcion genera una pregunta matematica en String
    * @return r Una operacion aritmetica entre dos numeros enteros
    **/
    public static String gen(){
        String r="";
        if (operacion == "SUMA"){
            r = n1 + " + " + n2;
        } else if (operacion == "RESTA") {
            r = n1 + " - " + n2;
        } else if (operacion == "MULTI") {
            r = n1 + " * " + n2;
        }
        return r;
    }

    private int resp(){
        return 1;
    }

}


