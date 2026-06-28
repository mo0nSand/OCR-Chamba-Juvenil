/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moon.controladores;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;

public class EventosDeVentana {
    public void CerrarVentana(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
}
