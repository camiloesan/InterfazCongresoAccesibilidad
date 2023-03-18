package mx.uv.fei.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AccessibilityCongressApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AccessibilityCongressApplication.class.getResource("congresoAccesibilidad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 745,  530);
        stage.setTitle("Congreso de Accesibilidad");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}