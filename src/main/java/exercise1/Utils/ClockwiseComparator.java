package exercise1.Utils;

import exercise1.Shapes.Point;

import java.util.Comparator;

public class ClockwiseComparator implements Comparator<Point> {
    private Point center;

    public ClockwiseComparator(Point center) {
        this.center = center;
    }

    @Override
    public int compare(Point o1, Point o2) {
        double angle1 = Math.atan2(o1.getY() - center.getY(), o1.getX() - center.getX());
        double angle2 = Math.atan2(o2.getY() - center.getY(), o2.getX() - center.getX());

        if (angle1 < angle2) return 1;
        else if (angle2 < angle1) return -1;
        return 0;
    }
}
