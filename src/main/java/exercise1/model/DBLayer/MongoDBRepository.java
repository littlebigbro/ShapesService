package exercise1.model.DBLayer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exercise1.IMVC.IModel;
import exercise1.model.Utils.Converter;
import exercise1.model.Shapes.Shape;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Updates.set;
//TODO: findQ.put("id", id) заменить "id" на "_id" из Монго;
@Repository
public class MongoDBRepository implements IModel {
    private boolean passwordIsCorrect = false;
    private MongoDB db;
    private String password;

    public MongoDBRepository() {
        db = new MongoDB();
    }

    public boolean enterPassword(String password) {
        if (db.checkConnection(password)) {
            this.password = password;
            passwordIsCorrect = true;
        }
        return passwordIsCorrect;
    }

    public void insert(Shape shape) {
        if (passwordIsCorrect) {
            Document document = Converter.ShapeToDocument(shape);
            db.establishDefaultConnection(password);
            db.getCollection().insertOne(document);
            db.closeConnection();
        }
    }

    public Shape createShape(String shapeName, List<Double> params) {
        int id = documentCount();
        return Converter.createShapeFactory(shapeName).createFigure(++id, params);
    }

    public int documentCount() {
        if (passwordIsCorrect) {
            db.establishDefaultConnection(password);
            long count = db.getCollection().countDocuments();
            db.closeConnection();
            return Math.toIntExact(count);
        } else return 0;
    }

    public void deleteById(int id) {
        if (passwordIsCorrect) {
            BasicDBObject findQ = new BasicDBObject();
            findQ.put("id", id);
            db.establishDefaultConnection(password);
            db.getCollection().findOneAndDelete(findQ);
            db.closeConnection();
        }
    }

    public List<Shape> findAll() {
        List<Shape> shapesList = new ArrayList<>();
        if (passwordIsCorrect) {
            db.establishDefaultConnection(password);
            ArrayList<Document> docList = db.getCollection().find().into(new ArrayList<>());
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

    public List<Shape> findById(int id) {
        List<Shape> shapesList = new ArrayList<>();
        if (passwordIsCorrect) {
            BasicDBObject findQ = new BasicDBObject();
            findQ.put("id", id);
            db.establishDefaultConnection(password);
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

    public void updateShape(Shape shape) {
        if (passwordIsCorrect) {
            int id = shape.getId();
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
            db.establishDefaultConnection(password);
            db.getCollection().updateOne(new Document("id", id), updatedParams);
            db.closeConnection();
        }
    }
//    Не уверен в необходимости
//    public void updateId(int id, Shape shape) {
//        int oldId = shape.getId();
//        shape.setId(id);
//        Document document = Converter.ShapeToDocument(shape);
//        Bson updateQ = set("id", document.getInteger("id"));
//        db.establishDefaultConnection(password);
//        db.getCollection().updateOne(new Document("id", oldId), updateQ);
//        db.closeConnection();
//    }
//
//    public void updateShapeType(Shape shape) {
//        int id = shape.getId();
//        Document document = Converter.ShapeToDocument(shape);
//        Bson updateQ = set("shapeType", document.getString("shapeType"));
//        db.establishDefaultConnection(password);
//        db.getCollection().updateOne(new Document("id", id), updateQ);
//        db.closeConnection();
//    }
//
//
//    public void updateRadius(Circle circle) {
//        int id = circle.getId();
//        Document document = Converter.ShapeToDocument(circle);
//        Bson updateQ = set("radius", document.getDouble("radius"));
//        db.establishDefaultConnection(password);
//        db.getCollection().updateOne(new Document("id", id), updateQ);
//        db.closeConnection();
//    }
//
//    public void updatePoints(Shape shape) {
//        int id = shape.getId();
//        Document document = Converter.ShapeToDocument(shape);
//        Bson updateQ = set("points", document.get("points"));
//        db.establishDefaultConnection(password);
//        db.getCollection().updateOne(new Document("id", id), updateQ);
//        db.closeConnection();
//    }

    public double calculateArea(String json) {
        Shape shape = Converter.jsonToShapes(json).get(0);
        return shape.calculateArea();
    }

    public Shape resizeShape(String json, double scale) {
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.changeSize(scale);
        return shape;
    }

    public Shape moveShape(String json, double x, double y) {
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.move(x, y);
        return shape;
    }

    public Shape rollShape(String json, double angle) {
        Shape shape = Converter.jsonToShapes(json).get(0);
        shape.roll(angle);
        return shape;
    }
}
