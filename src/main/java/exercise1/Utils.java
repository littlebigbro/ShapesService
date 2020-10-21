package exercise1;

import java.util.ArrayList;
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

    //Получает лист точек и возвращает лист всех возможных длин
    public static List<Double> calculateDistances(List<Point> points) {
        List<Double> distances = new ArrayList<Double>(); // ?? Если лист points пуст или size == 1 что вернуть.
        if (!points.isEmpty() && points.size() > 1) {
            Point point_1, point_2;
            for (int i = 0; i < points.size() - 2; i++) {
                point_1 = points.get(i);
                for (int j = i + 1; j < points.size() - 2; j++) {
                    point_2 = points.get(j);
                    double distance = Math.sqrt(Math.pow(point_2.getX() - point_1.getX(), 2) + Math.pow(point_2.getY() - point_1.getY(), 2));
                    distances.add(distance);
                }
            }
        }
        return distances;
    }
}
