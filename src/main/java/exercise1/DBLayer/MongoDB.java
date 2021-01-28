package exercise1.DBLayer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB {
    private MongoClient mongoClient;
    private MongoCollection <Document> collection;
    private String password;

    public MongoDB(String password) {
        this.password = password;
    }

    public void establishDefaultConnection() {
        String cS = "mongodb+srv://GoodCat:" + password + "@cluster0.jw84w.mongodb.net/shapesDB?retryWrites=true&w=majority";
        String db = "shapesDB";
        String cn = "shapes";
        establishConnection(cS,db,cn);
    }

    public void establishConnection(String connectionString, String dbName, String collectionName) {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        try {
            MongoClientURI uri = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            collection = database.getCollection(collectionName);
            System.out.println("Соединение успешно.");
        } catch (Exception e) {
            System.out.println("Ошибка соединения: ");
            e.printStackTrace();
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

}
