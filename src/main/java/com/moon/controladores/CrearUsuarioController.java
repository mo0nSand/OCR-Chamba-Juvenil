package com.moon.controladores;

import com.moon.beans.BnUsuarios;
import com.moon.modelos.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class CrearUsuarioController implements Initializable {
    @FXML
    private Label lbl_mensaje;
    @FXML
    private Button btnCrearUsuario;
    @FXML
    private TextField fieldPassword;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private AnchorPane principalCrearUsuarioPane;

    private final BnUsuarios bnUsuarios = new BnUsuarios();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    //crear clase para administrar los eventos de las ventanas -> 
    @FXML
    private void Cerrar(ActionEvent event) {
        EventosDeVentana ventana = new EventosDeVentana();
        ventana.CerrarVentana(event);
    }

    @FXML
    private void CrearUsuario(ActionEvent event) {
       String usuario = fieldUsuario.getText().trim();
        String password = fieldPassword.getText().trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            lbl_mensaje.setTextFill(Color.RED);
            lbl_mensaje.setText("Por favor, llena todos los campos.");
            return;
        }
        
        boolean exito = bnUsuarios.registrarUsuario(usuario, password);

        if (exito) {
            lbl_mensaje.setTextFill(Color.GREEN);
            lbl_mensaje.setText("¡Usuario creado con éxito!");
            fieldUsuario.clear();
            fieldPassword.clear();
            vistaSwitch.CambiarEscena(VistasRutas.LOGIN);
        } else {
            lbl_mensaje.setTextFill(Color.RED);
            lbl_mensaje.setText("El usuario ya existe.");
        }
    }
    
}
