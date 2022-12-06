/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.snake;

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
public class GenerarPreguntaTest {
    
    public GenerarPreguntaTest() {
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
     * Test de la funcion gen de la clase GenerarPregunta.
     * Verifica si el resultado de la operacion esta entre 0 y 20
     */
    @Test
    public void testGen() {
        System.out.println("gen");  
        boolean expResult = true;
        boolean result = false;
        String pregunta = GenerarPregunta.gen();
        int n1 = Integer.parseInt(pregunta.split(" ")[0]);
        int n2 = Integer.parseInt(pregunta.split(" ")[2]);
        String op = pregunta.split(" ")[1];
        if("+".equals(op)){
            result = n1+n2<=20 && n1+n2>0;
        }else if("-".equals(op)){
            result = n1-n2<=20 && n1-n2>0;
        }else if("x".equals(op)){
            result = n1*n2<=20 && n1*n2>0;
        }
        System.out.println(n1+op+n2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult!=result){
            fail("The test case is a prototype.");
        }
        
    }
    
}
