package exercise1.model.Factories;

import exercise1.model.Shapes.Shape;
import exercise1.model.Shapes.Triangle;

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
