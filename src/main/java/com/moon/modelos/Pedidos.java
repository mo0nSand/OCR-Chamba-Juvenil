
package com.moon.modelos;
public class Pedidos {
    
    private String etiqueta;
    private boolean estatus;
    private int IDPedido;
    
    public Pedidos(String etiqueta, boolean estatus, int IDPedido) {
        this.etiqueta = etiqueta;
        this.estatus = estatus;
        this.IDPedido = IDPedido;
    }
    
    public boolean isEstatus() {
        return estatus;
    }
    
    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }
}
