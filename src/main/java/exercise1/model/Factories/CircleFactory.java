package exercise1.model.Factories;

import exercise1.model.Shapes.Circle;
import exercise1.model.Shapes.Shape;
import org.bson.types.ObjectId;

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
