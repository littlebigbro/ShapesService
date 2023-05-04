package shapes.utils;

import shapes.models.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

public class Utils {

    public static Point calculateCenter(List<Point> points) {
        if (points.size() == 1) {
            return points.get(0);
        }
        double centerX = 0;
        double centerY = 0;
        for (Point point : points) {
            centerX += point.getX();
            centerY += point.getY();
        }
        Point center = new Point();
        center.setX(centerX / points.size());
        center.setY(centerY / points.size());
        return center;
    }

    /**
     * Возвращает округленное число типа double в формате #.##
     */
    public static Double roundDouble(Double param) {
        return BigDecimal.valueOf(param)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public static boolean collectionIsNotEmpty(Collection<?> collection) {
        return !collectionIsEmpty(collection);
    }

    public static boolean collectionIsEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
