module com.example.jafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.snake to javafx.fxml;
    exports com.example.snake;
}