package exercise1.model.DBLayer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exercise1.IMVC.IModel;
import exercise1.model.Shapes.Circle;
import exercise1.model.Shapes.Shape;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Updates.set;

public class MongoDBRepository implements IModel {
    //должны хранится методы для работы с базой
    private boolean passwordIsCorrect = false;
    private MongoDB db;
//    private String password;

    public MongoDBRepository() {
    }

//    public MongoDBRepository(String password) {
//        db = new MongoDB(password);
//        db.establishDefaultConnection();
//    }

    public void setPassword(String password) {
        db = new MongoDB(password);
        passwordIsCorrect = db.checkConnection();
    }
    public void insert(Shape shape) {
        Document document = Converter.ShapeToDocument(shape);
        db.establishDefaultConnection();
        db.getCollection().insertOne(document);
        db.closeConnection();
    }

    public int documentCount() {
        db.establishDefaultConnection();
        long count = db.getCollection().countDocuments();
        db.closeConnection();
        return Math.toIntExact(count);
    }

    public void deleteById(int id) {
        List<Shape> shapesList = new ArrayList<>();
        BasicDBObject findQ = new BasicDBObject();
        findQ.put("id", id);
        db.establishDefaultConnection();
        db.getCollection().findOneAndDelete(findQ);
        db.closeConnection();
    }

    public List<Shape> findAll() {
        List<Shape> shapesList = new ArrayList<>();
        db.establishDefaultConnection();
        ArrayList<Document> docList = db.getCollection().find().into(new ArrayList<>());
        if (!docList.isEmpty()) {
            for (int i = 0; i < docList.size(); i++) {
                DBObject dbObject = BasicDBObject.parse(docList.get(i).toJson());
                Shape shape = Converter.DBObjectToShape(dbObject);
                shapesList.add(shape);
            }
        }
        db.closeConnection();
        return shapesList;
    }

    public List<Shape> findById(int id) {
        List<Shape> shapesList = new ArrayList<>();
        BasicDBObject findQ = new BasicDBObject();
        findQ.put("id", id);
        db.establishDefaultConnection();
        ArrayList<Document> docList = db.getCollection().find(findQ).into(new ArrayList<>());
        if (!docList.isEmpty()) {
            for (int i = 0; i < docList.size(); i++) {
                DBObject dbObject = BasicDBObject.parse(docList.get(i).toJson());
                Shape shape = Converter.DBObjectToShape(dbObject);
                shapesList.add(shape);
            }
        }
        db.closeConnection();
        return shapesList;
    }

    public void updateId (int id, Shape shape) {
        int oldId = shape.getId();
        shape.setId(id);
        Document document = Converter.ShapeToDocument(shape);
        Bson updateQ = set("id", document.getInteger("id"));
        db.establishDefaultConnection();
        db.getCollection().updateOne(new Document("id", oldId), updateQ);
        db.closeConnection();
    }

//    public void updateShape(Shape shape) {
//        int id = shape.getId();
//        Document document = Converter.ShapeToDBObject(shape);
//        Bson updateQ = set("points", document.get("points"));
//        db.establishDefaultConnection();
//        db.getCollection().updateOne(new Document("id", id), updateQ);
//        db.closeConnection();
//    }

    public void updateShapeType(Shape shape) {
        int id = shape.getId();
        Document document = Converter.ShapeToDocument(shape);
        Bson updateQ = set("shapeType", document.getString("shapeType"));
        db.establishDefaultConnection();
        db.getCollection().updateOne(new Document("id", id), updateQ);
        db.closeConnection();
    }

    public boolean isPasswordIsCorrect() {
        return passwordIsCorrect;
    }

    public void updateRadius(Circle circle) {
        int id = circle.getId();
        Document document = Converter.ShapeToDocument(circle);
        Bson updateQ = set("radius", document.getDouble("radius"));
        db.establishDefaultConnection();
        db.getCollection().updateOne(new Document("id", id), updateQ);
        db.closeConnection();
    }

    public void updatePoints(Shape shape) {
        int id = shape.getId();
        Document document = Converter.ShapeToDocument(shape);
        Bson updateQ = set("points", document.get("points"));
        db.establishDefaultConnection();
        db.getCollection().updateOne(new Document("id", id), updateQ);
        db.closeConnection();
    }

    public Shape createShape(String shapeName, List<Double> params) {
        int id = documentCount();
        return Converter.createShapeFactory(shapeName).createFigure(++id , params);
    }
}
