package com.moon.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BnUsuarios {

    // Método auxiliar para obtener la conexión compartida
    private Connection getConexion() {
        return DatabaseManager.getInstancia().getConexion();
    }

    public boolean registrarUsuario(String nombre, String password) {
        String sqlVerificar = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
        String sqlInsertar = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)";
        
        // Obtenemos la conexión sin meterla en el try-with-resources para que NO se cierre
        Connection conn = getConexion();

        try (PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificar)) {
            
            stmtVerificar.setString(1, nombre);
            try (ResultSet rs = stmtVerificar.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // El usuario ya existe
                }
            }

            try (PreparedStatement stmtInsertar = conn.prepareStatement(sqlInsertar)) {
                stmtInsertar.setString(1, nombre);
                stmtInsertar.setString(2, password);
                
                int filasAfectadas = stmtInsertar.executeUpdate();
                return filasAfectadas > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(String nombre) {
        String sql = "DELETE FROM usuarios WHERE nombre = ?";
        Connection conn = getConexion();

        // Solo el PreparedStatement se cierra automáticamente aquí
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

   public ArrayList<com.moon.modelos.Usuarios> obtenerTodos() {
    ArrayList<com.moon.modelos.Usuarios> lista = new ArrayList<>();
    String sql = "SELECT nombre, password FROM usuarios";
    Connection conn = getConexion();

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            com.moon.modelos.Usuarios u = new com.moon.modelos.Usuarios("", "");
            u.setUsuario(rs.getString("nombre"));
            u.setPassword(rs.getString("password"));
            lista.add(u);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener usuarios: " + e.getMessage());
    }
    return lista;
}

    public boolean validarUsuario(String usuario, String password) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ? AND password = ?";
        Connection conn = getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al validar usuario en MySQL: " + e.getMessage());
        }
        return false;
    }
}