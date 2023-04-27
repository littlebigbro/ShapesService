package model.Factories;

import model.Shapes.Shape;
import model.Shapes.Triangle;

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
