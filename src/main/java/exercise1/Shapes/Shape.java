package exercise1.Shapes;

import exercise1.IActions.IMovable;
import exercise1.IActions.IRollable;
import exercise1.IActions.IScalebale;
import exercise1.Utils.ClockwiseComparator;

import java.util.Collections;
import java.util.List;

public abstract class Shape implements IMovable, IScalebale, IRollable {
    protected List<Point> points;
    protected Point center = new Point();
    protected double radius = 0;

    public double getRadius() {
        return radius;
    }

    public void getPoints() {
        for (Point point : points) {
            System.out.println("X - " + point.getX() + ", Y - " + point.getY());
        }
    }

    public double calculateArea() {
        //Формула площади Гаусса
        double a = 0;
        double b = points.get(points.size() - 1).getX() * points.get(0).getY();
        double c = 0;
        double d = points.get(0).getX() * points.get(points.size() - 1).getY();

        for (int i = 0; i < points.size() - 1; i++) {
            a += (points.get(i).getX() * points.get(i + 1).getY());
        }

        for (int i = 0; i < points.size() - 1; i++) {
            c += (points.get(i + 1).getX() * points.get(i).getY());
        }
        return Math.abs((a + b - c - d) / 2);
    }

}
