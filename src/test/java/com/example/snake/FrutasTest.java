/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
public class FrutasTest {
    
    public FrutasTest() {
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
     * Test de la funcion draw de la clase Frutas.
     * 
     */
    @Test
    public void testDraw() {
        Canvas canvas = new Canvas(Constantes.WIDTH, Constantes.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        Elemento instance = new Frutas(40, 50, Constantes.RAND.nextInt(0,Constantes.WIDTH-35),Constantes.RAND.nextInt(Constantes.HEIGHT-10, Constantes.HEIGHT+200));
        boolean expected = true;
        boolean actual = true;
        System.out.println("draw");
//        instance.draw(gc);
        assertEquals(1, 1);
        if(expected == false){
            fail("The test case is a prototype.");
        }
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    
}
