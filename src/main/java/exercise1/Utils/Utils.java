package exercise1.Utils;

import exercise1.Shapes.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static Point calculateCenter(List<Point> points) {
        double xC = 0;
        double yC = 0;
        for (Point point : points) {
            xC += point.getX();
            yC += point.getY();
        }
        return new Point(xC / points.size(), yC / points.size());
    }

    public static List<Point> toPoints(List<Double> params) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < params.size() - 1; i = i + 2) {
            Point point = new Point(params.get(i), params.get(i + 1));
            points.add(point);
        }
        return points;
    }

    public static double calculateDistanceBetweenToPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}
