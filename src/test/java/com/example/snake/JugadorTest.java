/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
public class JugadorTest {
    
    public JugadorTest() {
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
     * Test of draw method, of class Jugador.
     */
    @Test
    public void testDraw() {
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        boolean col = true;
        final Image PLAYER_IMAGE = new Image(getClass().getResourceAsStream("/img/jugador.png"));
        Jugador instance = new Jugador(20, 60,PLAYER_IMAGE);
        boolean expected = true;
        boolean actual = false;
        System.out.println("draw");
        try{
            instance.draw(gc, col);
            actual = true;
        }catch (Exception e){
            actual = false;
            System.out.println("Error al tratar de dibujar al jugador: " + e);
            fail("The test case is a prototype.");
        }

        assertEquals(expected, actual);
    }
    
}
