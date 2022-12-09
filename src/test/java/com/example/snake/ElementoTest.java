/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jdk.jfr.Experimental;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oscar
 */
public class ElementoTest {
    
    public ElementoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }


    /**
     * Test de la funcion update de la clase Elemento.
     * 
     */
    @Test
    public void testUpdate() {
        int speed = 2;
        int y = 90;
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Elemento instance = new Frutas(40, 50, Constantes.RAND.nextInt(0,Constantes.WIDTH-35),y);
        boolean expected = true;
        boolean actual = false;
        System.out.println("update");

        instance.update(speed);
        actual = instance.getPosY() == y - speed;

        assertEquals(actual, expected);
        if(expected != actual){
            fail("The test case is a prototype.");
        }

    }

    /**
     * Test de la funcion colision del jugador con un elemento de la clase Elemento.
     * 
     */
    @Test
    public void testColision() {
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int x = 8;
        final Image PLAYER_IMAGE = new Image(getClass().getResourceAsStream("/img/jugador.png"));
        Jugador j = new Jugador(x, 60,PLAYER_IMAGE);
        Elemento instance = new Frutas(40, 50, x,j.tamY);
        boolean expected = true;
        boolean actual = false;
        System.out.println("colision");

        actual = instance.colision(j);

        assertEquals(actual, expected);
        if(actual!=expected){
            fail("The test case is a prototype.");
        }

    }

    /**
     * Test de la funcion draw de la clase Elemento.
     * 
     */
    @Test
    public void testDraw() {
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Elemento instance = new Frutas(40, 50, Constantes.RAND.nextInt(0,Constantes.WIDTH-35),Constantes.RAND.nextInt(Constantes.HEIGHT-10, Constantes.HEIGHT+200));
        boolean expected = true;
        boolean actual = false;
        System.out.println("draw");
        try{
            instance.draw(gc);
            actual = true;
        }catch (Exception e){
            actual = false;
            System.out.println("Error al tratar de dibujar un elemento: " + e);
            fail("The test case is a prototype.");
        }

        assertEquals(expected, actual);

    }

    
}
