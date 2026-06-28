
package com.moon.modelos;

public class Usuarios {
    private String usuario;
    private String password;
    
    public Usuarios (String nombre, String password) {
    this.usuario = nombre;
    this.password = password;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}