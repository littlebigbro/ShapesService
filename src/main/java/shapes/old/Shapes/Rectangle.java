package shapes.old.Shapes;

import shapes.old.utils.ClockwiseComparator;
import shapes.old.utils.Utils;

import java.util.List;

public class Rectangle extends Shape {

    public Rectangle() {
    }

    public Rectangle(List<Double> params) {
        super(params);
        init();
    }

    public Rectangle(String _id, int id, List<Double> params) {
        super(_id, id, params);
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
