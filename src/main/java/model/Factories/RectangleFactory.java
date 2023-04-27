package model.Factories;

import model.Shapes.Rectangle;
import model.Shapes.Shape;

import java.util.List;

public class RectangleFactory implements IShapeFactory{
    @Override
    public Shape createFigure(List<Double> params) {
        return new Rectangle(params);
    }

    @Override
    public Shape createFigure(String _id, int id, List<Double> params) {
        return new Rectangle(_id, id, params);
    }
}
