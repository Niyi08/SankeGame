/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author loren
 */
public class Ejemplo_fx extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent  root = FXMLLoader.load(getClass().getResource("Ventana.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Prueba de bienvenida");
        stage.setScene(scene);
        stage.show();
    }
    
}
