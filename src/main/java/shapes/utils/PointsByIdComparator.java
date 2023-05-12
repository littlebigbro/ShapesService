package shapes.utils;

import shapes.models.Point;

import java.util.Comparator;

public class PointsByIdComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getPointId() < o2.getPointId()) {
            return -1;
        }
        if (o1.getPointId() > o2.getPointId()) {
            return 1;
        }
        return 0;
    }
}