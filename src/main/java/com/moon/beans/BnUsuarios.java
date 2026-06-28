package com.moon.beans;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.moon.modelos.Usuarios;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;


public class BnUsuarios {
    private final MongoCollection <Document> coleccion;
    public BnUsuarios() {
        this.coleccion = DatabaseManager.getInstancia()
                                        .getDatabase()
                                        .getCollection("usuarios");
    }
    

    public boolean registrarUsuario(String nombre, String password) {
        // Verificar si ya existe
        Document existe = coleccion.find(Filters.eq("nombre", nombre)).first();
        if (existe != null) {
            return false; 
        }

        Document nuevoUsuario = new Document("nombre", nombre)
                                    .append("password", password);
        coleccion.insertOne(nuevoUsuario);
        return true;
    }
 
    public boolean eliminarUsuario(String nombre) {
        DeleteResult resultado = coleccion.deleteOne(Filters.eq("nombre", nombre));
        return resultado.getDeletedCount() > 0;
    }

    public ArrayList<Document> obtenerTodos() {
        ArrayList<Document> lista = new ArrayList<>();
        try {
                return coleccion.find().into(lista);
        } catch (Exception e) {
           System.err.print(e);
        }
        return lista;
    }

    public boolean validarUsuario(String usuario, String password) {
     
        String uri = "mongodb://localhost:27017"; 
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("mi_aplicacion");
            MongoCollection<Document> collection = database.getCollection("usuarios");

            // Crear el filtro: buscar donde el campo 'usuario' y 'password' coincidan
            Document query = new Document("nombre", usuario)
                                 .append("password", password);

            // Intentar encontrar el primer documento que coincida
            Document usuarioEncontrado = collection.find(query).first();

            return usuarioEncontrado != null;

        } catch (Exception e) {
            System.err.println("Error conectando a MongoDB: " + e.getMessage());
            return false;
        }
    }
}