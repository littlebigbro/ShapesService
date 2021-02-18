package exercise1.model.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import exercise1.model.Factories.CircleFactory;
import exercise1.model.Factories.IShapeFactory;
import exercise1.model.Factories.RectangleFactory;
import exercise1.model.Factories.TriangleFactory;
import exercise1.model.Shapes.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Converter {

    public static Document ShapeToDocument(Shape shape) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("id", shape.getId());
        dbObject.put("shapeType", shape.getName());
        if (shape.getRadius() > 0) {
            dbObject.put("radius", shape.getRadius());
        }
        BasicDBList pointsList = new BasicDBList();
        for (Point point : shape.getPoints()) {
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
        ObjectId _id = (ObjectId) shapeDBObject.get("_id");
        int id = shapeDBObject.getInt("id");
        String shapeType = shapeDBObject.getString("shapeType");
        List<Double> shapeParams = new ArrayList<>();
        BasicDBList shapePointsList = (BasicDBList) shapeDBObject.get("points");
        for (Object object : shapePointsList) {
            BasicDBObject pointDBObject = (BasicDBObject) object;
            shapeParams.add(pointDBObject.getDouble("x"));
            shapeParams.add(pointDBObject.getDouble("y"));
        }
        if (shapeType.equalsIgnoreCase(ShapeTypes.CIRCLE.toString())) {
            shapeParams.add(shapeDBObject.getDouble("radius"));
        }
        return createShapeFactory(shapeType).createFigure(_id.toString(), id, shapeParams);
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

    public static String shapeToJSON(Shape shape) {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append("[");
        try {
            mapper.writeValue(writer, shape);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.append("]");
        return writer.toString();
    }

    public static String shapesListToJSON(List<Shape> shapes) {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.append("[");
        for (int i = 0; i < shapes.size(); i++) {
            try {
                mapper.writeValue(writer, shapes.get(i));
                if (i + 1 < shapes.size()) {
                    writer.append(", ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.append("]");
        return writer.toString();
    }

    public static List<Shape> jsonToShapes(String json) {
        List<Shape> tempShapes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            tempShapes = Arrays.asList(mapper.readValue(json, Shape[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Shape> resultShapes = new ArrayList<>();
        for(Shape shape : tempShapes) {
            if (ShapeTypes.CIRCLE.toString().equalsIgnoreCase(shape.getName())) {
                Circle circle = (Circle) shape;
                resultShapes.add(circle);
            } else if (ShapeTypes.TRIANGLE.toString().equalsIgnoreCase(shape.getName())) {
                Triangle triangle = (Triangle) shape;
                resultShapes.add(triangle);
            } else if (ShapeTypes.RECTANGLE.toString().equalsIgnoreCase(shape.getName())) {
                Rectangle rectangle = (Rectangle) shape;
                resultShapes.add(rectangle);
            }
        }
        return resultShapes;
    }

    /**
     * Ожидается, что в массив байтов преобразована строка в формате
     * param1=value1;param2=value2;...;paramN=valueN
     */
    public static Map<String, String> bytesToMap(byte[] bytes) {
        Map<String, String> map = new HashMap<>();
        String[] mapping = new String(bytes, StandardCharsets.UTF_8).split(";");
        for (String pair : mapping) {
            String[] params = pair.split("=");
            map.put(params[0], params[1]);
        }
        return map;
    }
}
