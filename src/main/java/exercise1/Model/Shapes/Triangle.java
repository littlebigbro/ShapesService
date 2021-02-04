package exercise1.Model.Shapes;

import exercise1.Model.Utils.ClockwiseComparator;
import exercise1.Model.Utils.Utils;

import java.util.List;

public class Triangle extends Shape {

    public Triangle() {
    }

    public Triangle(List<Double> params) {
        super(params);
        init();
    }

    public Triangle(int id, List<Double> params) {
        super(id, params);
        init();
    }

    private void init() {
        this.name = ShapeTypes.TRIANGLE.toString();
        this.ruName = ShapeTypes.TRIANGLE.getRuName();
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
//        X = x0 + (x - x0) * cos(a) - (y - y0) * sin(a);
//        Y = y0 + (y - y0) * cos(a) + (x - x0) * sin(a);
//          где, (x0, y0) — центр, точка вокруг которой нужно вращать
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
