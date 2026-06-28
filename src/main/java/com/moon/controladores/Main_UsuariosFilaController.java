package com.moon.controladores;

import com.moon.modelos.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Main_UsuariosFilaController implements Initializable {
    
    @FXML
    private Label lbl_usuario;
    @FXML
    private Label lbl_password;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_modificar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    void setDatos(Usuarios usuario) {
        this.lbl_usuario.setText(usuario.getUsuario());
        this.lbl_password.setText(usuario.getPassword());
    }
    
    @FXML
    void Eliminar() {
        System.out.println("eliminar");
    }
    
   @FXML
    void Modificar() {
        System.out.println("modificar");
    }
    
}
