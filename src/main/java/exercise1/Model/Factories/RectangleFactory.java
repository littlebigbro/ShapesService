package exercise1.Model.Factories;

import exercise1.Model.Shapes.Rectangle;
import exercise1.Model.Shapes.Shape;

import java.util.List;

public class RectangleFactory implements IShapeFactory{
    @Override
    public Shape createFigure(List<Double> params) {
        return new Rectangle(params);
    }

    @Override
    public Shape createFigure(int id, List<Double> params) {
        return new Rectangle(id, params);
    }
}
