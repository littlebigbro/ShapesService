package exercise1.Model.Factories;

import exercise1.Model.Shapes.Shape;

import java.util.List;

public interface IShapeFactory {
    Shape createFigure(List<Double> params);

    Shape createFigure(int id, List<Double> params);
}
