package com.moon.controladores;

import com.moon.beans.BnUsuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    public AnchorPane principalLoginPane;
    @FXML
    private Button btnCerrar;
    @FXML
    private Button btnIniciar;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private TextField fieldPassword;
    @FXML
    private Label lblMensaje;

    private double xApagar = 0;
    private double yApagar = 0;

    @FXML
    private void Cerrar(ActionEvent event) {
        EventosDeVentana ventana = new EventosDeVentana();
        ventana.CerrarVentana(event);
    }

    @FXML
    private void IniciarSesion(ActionEvent event) {
        String usuario = fieldUsuario.getText();
        String password = fieldPassword.getText();
        
        BnUsuarios bnUser = new BnUsuarios();
        
        if (bnUser.validarUsuario(usuario, password)) {
            System.out.println("login exitoso");
            try {
                  vistaSwitch.CambiarVentana(VistasRutas.PRINCIPAL);
            } catch (Exception ex) {
                System.getLogger(LoginController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos");
            lblMensaje.setText("Usuario o contraseña incorrectos");
            lblMensaje.setTextFill(Color.RED);
        }
    }
    
    @FXML 
    private void onCrearUsuario (Event e){
       vistaSwitch.CambiarEscena(VistasRutas.CREARUSUARIO);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        mainPane.setOnMousePressed(event -> {
//        xApagar = event.getSceneX();
//        yApagar = event.getSceneY();
//        });
//        
//        mainPane.setOnMouseDragged(event -> {
//        Stage stage = (Stage) mainPane.getScene().getWindow();
//        stage.setX(event.getScreenX() - xApagar);
//        stage.setY(event.getScreenY() - yApagar);
//        });

    }
}
