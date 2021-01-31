package exercise1.model.DBLayer;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exercise1.model.Factories.CircleFactory;
import exercise1.model.Factories.IShapeFactory;
import exercise1.model.Factories.RectangleFactory;
import exercise1.model.Factories.TriangleFactory;
import exercise1.model.Shapes.Point;
import exercise1.model.Shapes.Shape;
import exercise1.model.Shapes.ShapeTypes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static Document ShapeToDocument(Shape shape) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("id", shape.getId());
        dbObject.put("shapeType", shape.getName());
        if (shape.getRadius() > 0) {
            dbObject.put("radius", shape.getRadius());
        }
        BasicDBList pointsList = new BasicDBList();
        for(Point point : shape.getPoints()) {
            DBObject dbPoint = new BasicDBObject();
            dbPoint.put("x", point.getX());
            dbPoint.put("y", point.getY());
            pointsList.add(dbPoint);
        }
        dbObject.put("points", pointsList);
        return Document.parse(dbObject.toString());
    }

    public static Shape DBObjectToShape(DBObject dbObject) {
        BasicDBObject shapeDBObject = (BasicDBObject) dbObject;
        int shapeId = shapeDBObject.getInt("id");
        String shapeType = shapeDBObject.getString("shapeType");
        List<Double> shapeParams = new ArrayList<>();
        BasicDBList shapePointsList = (BasicDBList) shapeDBObject.get("points");
        for (Object object : shapePointsList) {
            BasicDBObject pointDBObject = (BasicDBObject) object;
            shapeParams.add(pointDBObject.getDouble("x"));
            shapeParams.add(pointDBObject.getDouble("y"));
        }
        if (shapeType.equalsIgnoreCase(ShapeTypes.CIRCLE.toString())){
            shapeParams.add(shapeDBObject.getDouble("radius"));
        }
        return createShapeFactory(shapeType).createFigure(shapeId, shapeParams);
    }

    public static IShapeFactory createShapeFactory(String factoryName) {
        if (ShapeTypes.CIRCLE.toString().equalsIgnoreCase(factoryName)) {
            return new CircleFactory();
        } else if (ShapeTypes.TRIANGLE.toString().equalsIgnoreCase(factoryName)) {
            return new TriangleFactory();
        } else if (ShapeTypes.RECTANGLE.toString().equalsIgnoreCase(factoryName)) {
            return new RectangleFactory();
        } else {
            throw new RuntimeException(factoryName + " неизвестная фигура");
        }
    }
}
