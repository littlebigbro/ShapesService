package exercise1.model.DBLayer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exercise1.IMVC.IModel;
import exercise1.model.Shapes.Shape;
import exercise1.model.Utils.Converter;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Updates.set;

@Repository
public class MongoDBRepository implements IModel {
    private boolean authCorrect = false;
    private MongoDB db;
    private String username;
    private String password;
    private BasicDBObject findQ;

    public MongoDBRepository() {
        db = new MongoDB();
    }

    public boolean login(Map<String, String> params) {
        this.username = params.get("username");
        this.password = params.get("password");
        if (db.checkConnection(this.username, this.password)) {
            authCorrect = true;
        } else {
            logout();
        }
        return authCorrect;
    }

    public void logout() {
        authCorrect = false;
        username = "";
        password = "";
    }

    public boolean insert(Shape shape) {
        if (authCorrect) {
            Document document = Converter.ShapeToDocument(shape);
            db.establishDefaultConnection(username, password);
            db.getCollection().insertOne(document);
            db.closeConnection();
            return true;
        }
        return false;
    }

    public int documentCount() {
        if (authCorrect) {
            db.establishDefaultConnection(username, password);
            long count = db.getCollection().countDocuments();
            db.closeConnection();
            return Math.toIntExact(count);
        } else return 0;
    }

    public boolean deleteById(String _id) {
        long deletedCount = 0;
        if (authCorrect) {
            findQ = new BasicDBObject("_id", new ObjectId(_id));
            db.establishDefaultConnection(username, password);
            deletedCount = db.getCollection().deleteOne(findQ).getDeletedCount();
            db.closeConnection();
        }
        return deletedCount > 0;
    }

    public List<Shape> findAll() {
        List<Shape> shapesList = new ArrayList<>();
        if (authCorrect) {
            db.establishDefaultConnection(username, password);
            ArrayList<Document> docList = db.getCollection().find().into(new ArrayList<>());
            db.closeConnection();
            if (!docList.isEmpty()) {
                for (Document document : docList) {
                    DBObject dbObject = BasicDBObject.parse(document.toJson());
                    Shape shape = Converter.DBObjectToShape(dbObject);
                    shapesList.add(shape);
                }
            }
        }
        return shapesList;
    }

    public List<Shape> findById(String _id) {
        List<Shape> shapesList = new ArrayList<>();
        if (authCorrect) {
            findQ = new BasicDBObject("_id", new ObjectId(_id));
            db.establishDefaultConnection(username, password);
            ArrayList<Document> docList = db.getCollection().find(findQ).into(new ArrayList<>());
            if (!docList.isEmpty()) {
                for (Document document : docList) {
                    DBObject dbObject = BasicDBObject.parse(document.toJson());
                    Shape shape = Converter.DBObjectToShape(dbObject);
                    shapesList.add(shape);
                }
            }
            db.closeConnection();
        }
        return shapesList;
    }

    public boolean updateShape(Shape shape) {
        if (authCorrect) {
            Document document = Converter.ShapeToDocument(shape);
            List<Bson> updatedParams = new ArrayList<>();
            if (document.containsKey("id")) {
                updatedParams.add(set("id", document.getInteger("id")));
            }
            if (document.containsKey("shapeType")) {
                updatedParams.add(set("shapeType", document.getString("shapeType")));
            }
            if (document.containsKey("radius")) {
                updatedParams.add(set("radius", document.getDouble("radius")));
            }
            if (document.containsKey("points")) {
                updatedParams.add(set("points", document.get("points")));
            }
            db.establishDefaultConnection(username, password);
            findQ = new BasicDBObject("_id", document.get("_id"));
            db.getCollection().updateOne(findQ, updatedParams);
            db.closeConnection();
            return true;
        }
        return false;
    }

    public int generateID() {
        int id = 0;
        if (authCorrect) {
            db.establishDefaultConnection(username, password);
            ArrayList<Document> docList = db.getCollection().find().limit(1).sort(new Document("_id", -1)).into(new ArrayList<>());
            db.closeConnection();
            if (!docList.isEmpty()) {
                for (Document document : docList) {
                    DBObject dbObject = BasicDBObject.parse(document.toJson());
                    Shape shape = Converter.DBObjectToShape(dbObject);
                    id = shape.getId();
                }
            }
        }
        return ++id;
    }

    public double calculateArea(String json) {
        Shape shape = Converter.jsonToShapes(json).get(0);
        return shape.calculateArea();
    }

    public Shape scaleShape(Map<String, String> params) {
        String json = params.get("json");
        double scale = Double.parseDouble(params.get("scale"));
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.changeSize(scale);
        return shape;
    }

    public Shape moveShape(Map<String, String> params) {
        String json = params.get("json");
        double x = Double.parseDouble(params.get("x"));
        double y = Double.parseDouble(params.get("y"));
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.move(x,y);
        return shape;
    }

    public Shape rollShape(Map<String, String> params) {
        String json = params.get("json");
        double angle = Double.parseDouble(params.get("angle"));
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.roll(angle);
        return shape;
    }
}
