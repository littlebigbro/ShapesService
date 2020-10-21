package exercise1;

import java.util.List;

public abstract class Shape {
    protected List<Point> points;
    protected Point center;
    protected double radius;
    protected abstract double calculateArea();
}
