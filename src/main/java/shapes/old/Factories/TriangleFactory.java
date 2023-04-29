package shapes.old.Factories;

import shapes.old.Shapes.Shape;
import shapes.old.Shapes.Triangle;

import java.util.List;

public class TriangleFactory implements IShapeFactory{
    @Override
    public Shape createFigure(List<Double> params) {
        return new Triangle(params);
    }

    @Override
    public Shape createFigure(String _id, int id, List<Double> params) {
        return new Triangle(_id, id, params);
    }
}
