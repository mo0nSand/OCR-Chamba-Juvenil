package com.moon.controladores;

import com.moon.beans.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import javafx.scene.layout.Pane;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new Pane());
        vistaSwitch.setScene(scene);
        vistaSwitch.CambiarEscena(VistasRutas.LOGIN);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
 
    }

//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//private static Parent loadFXML(String fxml) throws IOException {
//    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/vistas/" + fxml + ".fxml"));
//    return fxmlLoader.load();
//}

    public static void main(String[] args) {
        DatabaseManager.getInstancia();
        launch(args);
    }

}