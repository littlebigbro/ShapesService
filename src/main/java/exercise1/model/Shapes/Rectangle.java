package exercise1.model.Shapes;

import exercise1.model.Utils.ClockwiseComparator;
import exercise1.model.Utils.Utils;

import java.util.List;

public class Rectangle extends Shape {

    public Rectangle() {
    }

    public Rectangle(List<Double> params) {
        super(params);
        init();
    }

    public Rectangle(int id, List<Double> params) {
        super(id, params);
        init();
    }

    private void init() {
        this.name = ShapeTypes.RECTANGLE.toString();
        this.ruName = ShapeTypes.RECTANGLE.getRuName();
        points = Utils.toPoints(params);
        center = Utils.calculateCenter(points);
        points.sort(new ClockwiseComparator(center));
    }

    @Override
    public void move(double x, double y) {
        double dX = x - this.center.getX();
        double dY = y - this.center.getY();
        for (Point point : points) {
            point.setX(point.getX() + dX);
            point.setY(point.getY() + dY);
        }
        this.center.setX(x);
        this.center.setY(y);
    }

    @Override
    public void roll(double angle) {
        for (Point point : points) {
            point.setX(center.getX() + (point.getX() - center.getX()) * Math.cos(angle) - (point.getY() - center.getY()) * Math.sin(angle));
            point.setY(center.getY() + (point.getY() - center.getY()) * Math.cos(angle) + (point.getX() - center.getX()) * Math.sin(angle));

        }
    }

    @Override
    public void changeSize(double scaleFactor) {
        for (Point point : points) {
            point.setX(center.getX() + scaleFactor * (point.getX() - center.getX()));
            point.setY(center.getY() + scaleFactor * (point.getY() - center.getY()));
        }
    }
}
