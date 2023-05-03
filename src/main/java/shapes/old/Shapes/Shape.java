package shapes.old.Shapes;

import shapes.models.Point;
import shapes.old.utils.Utils;

import java.util.List;

public abstract class Shape {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String ruName;
    protected List<Point> points;
    protected List<Double> params;
    protected Point center = new Point();
    protected double radius = 0;
    protected String _id;
    protected int id;
    private static int IDCounter;


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
        return Utils.roundDouble(Math.abs((a + b - c - d) / 2));
    }

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


    public void changeSize(double scaleFactor) {
        for (Point point : points) {
            double tempX = center.getX() + scaleFactor * (point.getX() - center.getX());
            double tempY = center.getY() + scaleFactor * (point.getY() - center.getY());
            point.setX(tempX);
            point.setY(tempY);
        }
    }
}
