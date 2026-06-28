package com.moon.controladores;

public enum VistasRutas {
    LOGIN("/vistas/Login.fxml"),
    PRINCIPAL("/vistas/vistaMain.fxml"),
    GESTION_PEDIDOS("/vistas/main_gestionPedidos.fxml"),
    GESTION_USUARIOS("/vistas/main_gestionUsuarios.fxml"),
    FILA_USUARIO("/vistas/main_UsuariosFila.fxml"),
    CREARUSUARIO("/vistas/crearUsuario.fxml");
    
    private String nombreArchivo;
    
    VistasRutas(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
    }
    
    public String getNombreArchivo () {
    return this.nombreArchivo;
    }
}
