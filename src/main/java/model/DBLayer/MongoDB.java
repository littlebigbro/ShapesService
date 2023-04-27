package model.DBLayer;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    private String username;
    private String password;
    private long docCount;
    private boolean connectionError = false;

    public MongoDB() {
    }

    public MongoDB(String password) {
        this.password = password;
    }

    public void establishDefaultConnection(String username, String password) {
        this.username = username;
        this.password = password;
        String cS = "mongodb+srv://" + username + ":" + password + "@cluster0.jw84w.mongodb.net/shapesDB?retryWrites=true&w=majority";
        byte[] bytes = cS.getBytes(StandardCharsets.UTF_8);

        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
        String db = "shapesDB";
        String cn = "shapes";
        establishConnection(utf8EncodedString, db, cn);
    }

    public void establishConnection(String connectionString, String dbName, String collectionName) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        try {
            MongoClientURI uri = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            collection = database.getCollection(collectionName);
            docCount = collection.countDocuments();
            connectionError = false;
        } catch (Exception e) {
            connectionError = true;
        }
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public boolean checkConnection(String username, String password) {
        establishDefaultConnection(username, password);
        if (!connectionError) {
            closeConnection();
            return true;
        }
        return false;
    }

    public long getDocCount() {
        return docCount;
    }
}
