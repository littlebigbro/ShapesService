package exercise1.triangle;

import exercise1.*;

import java.util.List;

public class Triangle extends Shape implements IShapeStateFactory {
    private double a;
    private double b;
    private double c;

    public Triangle(List<Point> points) {
        center = Utils.calculateCenter(points); //Возвращает точку
        List<Double> distances = Utils.calculateDistances(points);
        if (!distances.isEmpty()) {
            a = distances.get(0);
            b = distances.get(1);
            c = distances.get(2);
        }
    }

    @Override
    public IMovable setMove() {
        return new Move();
    }

    @Override
    public IScalebale setScale() {
        return new Scale();
    }

    @Override
    public IRollable setRoll() {
        return new Roll();
    }

    @Override
    protected double calculateArea() {
        double halfPerim = (a + b + c) / 2.0;
        double area = Math.sqrt(halfPerim * (halfPerim - a) * (halfPerim - b) * (halfPerim - c));
        return area;
    }
}
