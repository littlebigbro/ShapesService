package shapes.old.Shapes;

import shapes.old.utils.ClockwiseComparator;
import shapes.old.utils.Utils;

import java.util.List;

public class Triangle extends Shape {

    public Triangle() {
    }

    public Triangle(List<Double> params) {
        super(params);
        init();
    }

    public Triangle(String _id, int id, List<Double> params) {
        super(_id, id, params);
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
        double radian = Math.toRadians(angle);
        for (Point point : points) {
            double tempX = center.getX() + ((point.getX() - center.getX()) * Math.cos(radian)) - ((point.getY() - center.getY()) * Math.sin(radian));
            double tempY = center.getY() + ((point.getY() - center.getY()) * Math.cos(radian)) + ((point.getX() - center.getX()) * Math.sin(radian));
            point.setX(tempX);
            point.setY(tempY);
        }
    }

    @Override
    public void changeSize(double scaleFactor) {
        for (Point point : points) {
            double tempX = center.getX() + scaleFactor * (point.getX() - center.getX());
            double tempY = center.getY() + scaleFactor * (point.getY() - center.getY());
            point.setX(tempX);
            point.setY(tempY);
        }
    }
}
