package exercise1.model.Factories;

import exercise1.model.Shapes.Shape;

import java.util.List;

public interface IShapeFactory {
    Shape createFigure(List<Double> params);

    Shape createFigure(int id, List<Double> params);
}
