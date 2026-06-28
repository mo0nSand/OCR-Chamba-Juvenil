package com.moon.beans;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseManager {
    private static DatabaseManager instancia;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private final String URI = "mongodb://localhost:27017";
    private final String DB_NAME = "mi_aplicacion";

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
            this.mongoClient = MongoClients.create(URI);
            this.database = mongoClient.getDatabase(DB_NAME);

            // Forzar la creación verificando/creando la colección
            boolean existe = database.listCollectionNames()
                                     .into(new java.util.ArrayList<>())
                                     .contains("usuarios");
            if (!existe) {
                database.createCollection("usuarios");
                System.out.println("Colección 'usuarios' inicializada con éxito.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }
}