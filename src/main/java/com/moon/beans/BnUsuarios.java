package com.moon.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BnUsuarios {

    private Connection getConexion() {
        return DatabaseManager.getInstancia().getConexion();
    }

    public boolean registrarUsuario(String nombre, String apellido, String usuario, String clave, String rol, String estado) {
        String sqlVerificar = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        String sqlInsertar = "INSERT INTO usuarios (nombre, apellido, usuario, clave, rol, estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = getConexion();

        try (PreparedStatement stmtVerificar = conn.prepareStatement(sqlVerificar)) {
            stmtVerificar.setString(1, usuario);
            try (ResultSet rs = stmtVerificar.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }

            try (PreparedStatement stmtInsertar = conn.prepareStatement(sqlInsertar)) {
                stmtInsertar.setString(1, nombre);
                stmtInsertar.setString(2, apellido);
                stmtInsertar.setString(3, usuario);
                stmtInsertar.setString(4, clave);
                stmtInsertar.setString(5, rol);
                stmtInsertar.setString(6, estado);
                
                int filasAfectadas = stmtInsertar.executeUpdate();
                return filasAfectadas > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(String usuario) {
        String sql = "DELETE FROM usuarios WHERE usuario = ?";
        Connection conn = getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<com.moon.modelos.Usuarios> obtenerTodos() {
        ArrayList<com.moon.modelos.Usuarios> lista = new ArrayList<>();
        String sql = "SELECT usuario, clave FROM usuarios";
        Connection conn = getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                com.moon.modelos.Usuarios u = new com.moon.modelos.Usuarios("", "");
                u.setUsuario(rs.getString("usuario"));  
                u.setPassword(rs.getString("clave"));
                lista.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    public boolean validarUsuario(String usuario, String clave) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND clave = ?";
        Connection conn = getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, clave);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al validar usuario en MariaDB: " + e.getMessage());
        }
        return false;
    }
}