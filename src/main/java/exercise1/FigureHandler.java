package exercise1;

import exercise1.Factories.CircleFactory;
import exercise1.Factories.IShapeFactory;
import exercise1.Factories.RectangleFactory;
import exercise1.Factories.TriangleFactory;
import exercise1.Shapes.Shape;
import exercise1.Utils.FileAction;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FigureHandler {

    private List<Shape> figureList = new ArrayList<>();

    public void handle(String filePath) {
        InputStream is = getClass().getResourceAsStream(filePath);
        List<String> dataFromFile = FileAction.readFileToList(is);
        for (String line : dataFromFile) {
            String[] lineAsMassive= line.split("[ ]");
            IShapeFactory shapeFactory = createShapeFactory(parseName(lineAsMassive));
            figureList.add(shapeFactory.createFigure(parsePointValues(lineAsMassive)));
        }
    }

    private String parseName(String[] line) {
        return line[0];
    }

    private List<Double> parsePointValues(String[] line) {
        List <Double> pointValues = new ArrayList<>();
        for (int i = 1; i < line.length; i++) { // 0 элемент это наименование фигуры.
            pointValues.add(Double.parseDouble(line[i]));
        }
        return pointValues;
    }

    public static IShapeFactory createShapeFactory(String factoryName) {
        if ("circle".equalsIgnoreCase(factoryName)) {
            return new CircleFactory();
        } else if ("triangle".equalsIgnoreCase(factoryName)) {
            return new TriangleFactory();
        } else if ("rectangle".equalsIgnoreCase(factoryName)) {
            return new RectangleFactory();
        } else {
            throw new RuntimeException(factoryName + " is unknown figure");
        }
    }

    public List<Shape> getFigureList() {
        return figureList;
    }
}
