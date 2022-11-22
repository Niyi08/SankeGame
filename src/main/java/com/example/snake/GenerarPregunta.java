package com.example.snake;

//Clase que genera una pregunta matematica aleatoria
public class GenerarPregunta {

    static String[] operaciones = {"SUMA", "RESTA", "MULTI"};
    public static int n1 = (int)(Math.random()*10+1);
    public static int n2 = (int)(Math.random()*10+1);
    public static String operacion = operaciones[(int)(Math.random()*3)];

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


