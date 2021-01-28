package exercise1.Factories;

import exercise1.Shapes.Shape;
import exercise1.Shapes.Triangle;

import java.util.List;

public class TriangleFactory implements IShapeFactory{
    @Override
    public Shape createFigure(List<Double> params) {
        return new Triangle(params);
    }

    @Override
    public Shape createFigure(int id, List<Double> params) {
        return new Triangle(id, params);
    }
}
