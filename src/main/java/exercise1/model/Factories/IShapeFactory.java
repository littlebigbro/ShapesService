package exercise1.model.Factories;

import exercise1.model.Shapes.Shape;
import org.bson.types.ObjectId;

import java.util.List;

public interface IShapeFactory {
    Shape createFigure(List<Double> params);

    Shape createFigure(String _id, int id, List<Double> params);
}
