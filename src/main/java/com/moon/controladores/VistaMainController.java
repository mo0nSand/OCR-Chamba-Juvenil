
package com.moon.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VistaMainController implements Initializable {
    @FXML
    private Button btn_gestionUsuarios;
    @FXML
    private Button btn_pedidos;
    @FXML
    private Button btn_cerrar;
    @FXML
     public BorderPane principalMainPane;
     
        @FXML
    void OnGestionPedidos(ActionEvent event) {
            vistaSwitch.CambiarContenedorPrincipal(VistasRutas.GESTION_PEDIDOS, principalMainPane);
    }
    @FXML
    void onGestionUsuarios(ActionEvent event) {
    vistaSwitch.CambiarContenedorPrincipal(VistasRutas.GESTION_USUARIOS, principalMainPane);
    }
    
    @FXML
    private void CerrarSesion (ActionEvent event) {
        vistaSwitch.CambiarVentana(VistasRutas.LOGIN);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vistaSwitch.CambiarContenedorPrincipal(VistasRutas.GESTION_USUARIOS, principalMainPane);
        Platform.runLater(()->{
        Stage stage = (Stage) principalMainPane.getScene().getWindow();
        stage.setMaximized(true);
        });
    btn_cerrar.setOnAction((e) -> {
        EventosDeVentana ventana = new EventosDeVentana();
        ventana.CerrarVentana(e);
    });
    }    
}
