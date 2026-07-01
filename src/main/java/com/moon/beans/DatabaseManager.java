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
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión a MariaDB establecida con éxito.");

        // Ejecutamos la creación de todo el esquema
        crearEsquemaTablas();

    } catch (ClassNotFoundException e) {
        System.err.println("Error: No se encontró el driver.");
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
    private void crearEsquemaTablas() {

    String sqlEstructuras = "CREATE TABLE IF NOT EXISTS estructuras ("
               + "id_estructura INT AUTO_INCREMENT PRIMARY KEY, "
               + "nombre_estructura VARCHAR(255) NOT NULL, "
               + "descripcion TEXT"
               + ");";


    String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
               + "id_usuario INT AUTO_INCREMENT PRIMARY KEY, "
               + "nombre VARCHAR(255) NOT NULL, "
               + "apellido VARCHAR(255) NOT NULL, "
               + "usuario VARCHAR(100) UNIQUE NOT NULL, "
               + "clave VARCHAR(255) NOT NULL, "
               + "rol VARCHAR(50), "
               + "estado VARCHAR(50)"
               + ");";


    String sqlPersonal = "CREATE TABLE IF NOT EXISTS personal_chamba ("
               + "id_personal INT AUTO_INCREMENT PRIMARY KEY, "
               + "estatus VARCHAR(50), "
               + "nombre VARCHAR(255) NOT NULL, "
               + "apellido VARCHAR(255) NOT NULL, "
               + "cedula VARCHAR(20) UNIQUE, "
               + "nro_telefono VARCHAR(20), "
               + "correo VARCHAR(255), "
               + "talla_camisa VARCHAR(10), "
               + "talla_pantalon VARCHAR(10), "
               + "id_estructura INT NOT NULL, "
               + "responsabilidades TEXT, "
               + "nro_cuenta VARCHAR(50), "
               + "direccion TEXT, "
               + "medio_transporte VARCHAR(100), "
               + "patologia TEXT, "
               + "fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP, "
               + "CONSTRAINT fk_personal_estructura FOREIGN KEY (id_estructura) "
               + "REFERENCES estructuras(id_estructura) "
               + "ON DELETE RESTRICT ON UPDATE CASCADE"
               + ");";

    try (Statement stmt = conexion.createStatement()) {
        stmt.executeUpdate(sqlEstructuras);
        stmt.executeUpdate(sqlUsuarios);
        stmt.executeUpdate(sqlPersonal);
        System.out.println("Esquema de base de datos verificado/creado con éxito.");
    } catch (SQLException e) {
        System.err.println("Error al crear el esquema de tablas.");
        e.printStackTrace();
    }
}

}