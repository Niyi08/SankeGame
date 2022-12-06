package com.example.snake;

/**
 * Clase que genera una pregunta matematica
 * 
 * @author Oscar Castillejo Rodriguez, Juliana Castaño Aguirre, Lorena Cortes Ballesteros, Fredy Cuesta Mena, Cristian Cuenca Trujillo
 **/

public class GenerarPregunta {

    static String[] operaciones = {"SUMA", "RESTA", "MULTI"};

    /**
    * Esta funcion genera una pregunta matematica en String
    * @return r Una operacion aritmetica entre dos numeros enteros
    **/
    public static String gen(){
        String r="";
        String operacion = operaciones[(int)(Math.random()*3)];
        int n1 = (int)(Math.random()*10+1);
        int n2 = (int)(Math.random()*10+1);
        if(operacion == "SUMA"){
            r = n1 + " + " + n2;
        }else if(operacion == "RESTA"){
            if(n1-n2<0){
                n1 = n1 ^ n2 ^ (n2 = n1);
                r = n1 + " - " + n2;
            }else{
                r = n1 + " - " + n2;
            }
        } else if (operacion == "MULTI") {
            if(n1*n2>20){
                n1 = (int)(Math.random()*5+1);
                n2 = (int)(Math.random()*4+1);
                r = n1 + " x " + n2;
            }else{
                r = n1 + " x " + n2;
            }
        }
        return r;
    }
}


