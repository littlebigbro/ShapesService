package exercise1.rectangle;

import exercise1.*;

import java.util.Collections;
import java.util.List;

public class Rectangle extends Shape implements IShapeStateFactory {
    private double a;
    private double b;
    private double diag;

    public Rectangle(List<Point> points) {
        center = Utils.calculateCenter(points);
        List<Double> distances = Utils.calculateDistances(points);
        Collections.sort(distances);
        if (!distances.isEmpty()) {
            a = distances.get(0);  //переписать красиво
            for (double distance : distances) {
                if (distance > diag) {
                    b = diag;
                    diag = distance;
                }
            }
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
        return a * b;
    }
}
