package shapes.old.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import shapes.old.Shapes.*;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Converter {

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
        for (Shape shape : tempShapes) {
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
