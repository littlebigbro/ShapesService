package shapes.utils;

import lombok.AllArgsConstructor;
import shapes.models.Point;

import java.util.Comparator;

@AllArgsConstructor
public class ClockwiseComparator implements Comparator<Point> {
    private final Point center;

    @Override
    public int compare(Point o1, Point o2) {
        double angle1 = getAngle(o1);
        double angle2 = getAngle(o2);

        if (angle1 < angle2) {
            return 1;
        }
        if (angle2 < angle1) {
            return -1;
        }
        return 0;
    }

    private double getAngle(Point point) {
        return Math.atan2(point.getY() - center.getY(), point.getX() - center.getX());
    }
}
