package shapes.old.Factories;

import shapes.old.Shapes.Shape;

import java.util.List;

public interface IShapeFactory {
    Shape createFigure(List<Double> params);

    Shape createFigure(String _id, int id, List<Double> params);
}
