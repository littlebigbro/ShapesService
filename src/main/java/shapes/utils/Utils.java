package shapes.utils;

import shapes.models.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public static boolean isNotEmpty(List<?> lst) {
        return !isEmpty(lst);
    }

    public static boolean isEmpty(List<?> lst) {
        return lst == null || lst.isEmpty();
    }
}
