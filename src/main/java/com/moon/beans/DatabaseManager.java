package com.moon.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instancia;
    private Connection conexion;

    // Configuración para MySQL
    private final String URL = "jdbc:mysql://localhost:3306/chamba_juvenil?createDatabaseIfNotExist=true&serverTimezone=UTC";
    private final String USER = "root";       
    private final String PASSWORD = "root";

    // Constructor privado para el Singleton
    private DatabaseManager() {
        inicializarBaseDatos();
    }

    public static synchronized DatabaseManager getInstancia() {
        if (instancia == null) {
            instancia = new DatabaseManager();
        }
        return instancia;
    }

    private void inicializarBaseDatos() {
        try {
            // 1. Registrar el driver de MySQL (opcional en versiones modernas de JDBC, pero buena práctica)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Establecer la conexión
            this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a MySQL establecida con éxito.");

            // 3. Crear la tabla 'usuarios' si no existe (equivalente a tu lógica de MongoDB)
            crearTablaUsuariosSiNoExiste();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL. Asegúrate de agregar la dependencia.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar o inicializar la base de datos.");
            e.printStackTrace();
        }
    }

    private void crearTablaUsuariosSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY, "
                   + "nombre VARCHAR(100) NOT NULL, "
                   + "email VARCHAR(100) UNIQUE NOT NULL"
                   + ");";

        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla 'usuarios' verificada/creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla 'usuarios'.");
            e.printStackTrace();
        }
    }

    // Método para obtener la conexión y realizar consultas en otras partes de tu app
    public Connection getConexion() {
        try {
            // Verifica si la conexión se cerró por inactividad y la reabre si es necesario
            if (this.conexion == null || this.conexion.isClosed()) {
                this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.conexion;
    }
}