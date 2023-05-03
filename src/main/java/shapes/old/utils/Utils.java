package shapes.old.utils;

import shapes.models.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Utils {

    public static Point calculateCenter(List<Point> points) {
        double centerX = 0;
        double centerY = 0;
        for (Point point : points) {
            centerX += point.getX();
            centerY += point.getY();
        }
        Point p = new Point();
        p.setX(centerX / points.size());
        p.setY(centerY / points.size());
        return p;
    }
    /**
     * Возвращает округленное число типа double в формате #.##
     */
    public static double roundDouble(double param) {
        return BigDecimal.valueOf(param)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
