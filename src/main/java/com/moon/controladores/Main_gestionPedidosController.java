package com.moon.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main_gestionPedidosController implements Initializable {

    @FXML
    private AnchorPane PrincipalMain_GestionUsuarioPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PrincipalMain_GestionUsuarioPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

}
