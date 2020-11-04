package exercise1.Shapes;

import exercise1.Utils.ClockwiseComparator;
import exercise1.Utils.Utils;

import java.util.Collections;
import java.util.List;

public class Rectangle extends Shape {

    public Rectangle(List<Double> params) {
        points = Utils.toPoints(params);
        center = Utils.calculateCenter(points);
        Collections.sort(points, new ClockwiseComparator(center));
        System.out.println(points);
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
//        System.out.println("Прямоугольник крутится лавэха мутится");
    }

    @Override
    public void changeSize(double scaleFactor) {
        for (Point point : points) {
            point.setX(center.getX() + scaleFactor * (point.getX() - center.getX()));
            point.setY(center.getY() + scaleFactor * (point.getY() - center.getY()));
        }
    }
}
