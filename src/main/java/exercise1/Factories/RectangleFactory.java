package exercise1.Factories;

import exercise1.Shapes.Rectangle;
import exercise1.Shapes.Shape;

import java.util.List;

public class RectangleFactory implements IShapeFactory{
    @Override
    public Shape createFigure(List<Double> params) {
        return new Rectangle(params);
    }
}
