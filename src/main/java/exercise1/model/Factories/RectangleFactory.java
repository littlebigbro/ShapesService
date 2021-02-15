package exercise1.model.Factories;

import exercise1.model.Shapes.Rectangle;
import exercise1.model.Shapes.Shape;
import org.bson.types.ObjectId;

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
