package com.moon.controladores;

import com.moon.beans.BnUsuarios;
import com.moon.modelos.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.bson.Document;

public class Main_gestionUsuariosController extends ListCell<Usuarios> implements Initializable {

    @FXML
    private AnchorPane PrincipalMain_gestionUsuariosPane;
    @FXML
    private VBox ContenedorDeUsuarios;

    private FXMLLoader loader;
    
    public void cargarUsuarios(List<Usuarios> listaUsuarios) {

        ContenedorDeUsuarios.getChildren().clear();
                   // u = listaUsuarios[i]
        for (Usuarios u : listaUsuarios) {
            try {
                String ruta = VistasRutas.FILA_USUARIO.getNombreArchivo();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
                Node fila = loader.load();

                
                Main_UsuariosFilaController controller = loader.getController();
                controller.setDatos(u);

                
                ContenedorDeUsuarios.getChildren().add(fila);

            } catch (IOException e) {
                System.err.println("Error al cargar la fila para: " + u.getUsuario());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BnUsuarios bnUser = new BnUsuarios(); 
    
    ArrayList<Document> listaDocumentos = bnUser.obtenerTodos();
    
    List<Usuarios> listaUsuarios = new ArrayList<>();
    
    for (Document doc : listaDocumentos) {
        Usuarios u = new Usuarios("","");
        
        u.setUsuario(doc.getString("nombre")); 
        u.setPassword(doc.getString("password"));
     
        // u.setId(doc.getObjectId("_id").toHexString());
        
        listaUsuarios.add(u);
    }
        
        cargarUsuarios(listaUsuarios);
        PrincipalMain_gestionUsuariosPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
