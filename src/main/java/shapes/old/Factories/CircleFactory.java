package shapes.old.Factories;

import shapes.old.Shapes.Circle;
import shapes.old.Shapes.Shape;

import java.util.List;

public class CircleFactory implements IShapeFactory {

    @Override
    public Shape createFigure(List<Double> params) {
        return new Circle(params);
    }

    @Override
    public Shape createFigure(String _id, int id, List<Double> params) {
        return new Circle(_id, id, params);
    }
}
