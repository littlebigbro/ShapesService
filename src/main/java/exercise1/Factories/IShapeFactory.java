package exercise1.Factories;

import exercise1.Shapes.Shape;

import java.util.List;

public interface IShapeFactory {
    Shape createFigure(List<Double> params);
}
