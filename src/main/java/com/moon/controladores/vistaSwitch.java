package com.moon.controladores;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class vistaSwitch {

    private static Scene scene;

    public static void setScene(Scene scene) {
        vistaSwitch.scene = scene;
    }

    public static void CambiarVentana(VistasRutas vista) {
        if (scene == null) {
            System.out.println("sin escena base seteada");
            return;
        }
        try {
            Parent root = FXMLLoader.load(vistaSwitch.class.getResource(vista.getNombreArchivo()));
            Scene nuevaEscena = new Scene(root);

            Stage nuevoStage = new Stage();
            nuevoStage.setScene(nuevaEscena);

            Stage stageAnterior = (Stage) scene.getWindow();

            if (stageAnterior != null) {
                stageAnterior.close();
            }

            setScene(nuevaEscena);
            scene = nuevaEscena;
            nuevoStage.initStyle(StageStyle.UNDECORATED);
            nuevoStage.show();

        } catch (IOException e) {
            System.out.println("Error al abrir la nueva ventana: " + e.getMessage());
        }
    }

    public static void CambiarEscena(VistasRutas vista) {
        if (scene == null) {
            System.out.println("la escena no ha sido seteada");
            return;
        }
        try {
            Parent root = FXMLLoader.load(vistaSwitch.class.getResource(vista.getNombreArchivo()));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CambiarContenedorPrincipal(VistasRutas vista, BorderPane borderpane) {
        try {
            Parent vistaSecundaria = FXMLLoader.load(vistaSwitch.class.getResource(vista.getNombreArchivo()));
            borderpane.setCenter(vistaSecundaria);
            
        } catch (IOException e) {
            System.err.println("Error al cargar la pestaña: " + vista.getNombreArchivo());
            e.printStackTrace();
        }
    }
}
