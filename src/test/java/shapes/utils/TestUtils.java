package shapes.utils;

import shapes.models.Point;
import shapes.models.RadiusInfo;
import shapes.models.Shape;
import shapes.models.ShapeType;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.UpdatePointDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtils {
    public static ShapeType createShapeType(Long id, Integer pointsCount) {
        return new ShapeType(
                id, "SystemName_" + id, "Name_" + id,
                LocalDateTime.now(), LocalDateTime.now(), pointsCount
        );
    }


    public static List<Point> createPointList(Long count, Shape shape) {
        List<Point> lst = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lst.add(new Point(count, count * 1.0, count * 1.0, shape));
        }
        return lst;
    }

    public static List<CreatePointDTO> createCreatePointList(Integer count) {
        List<CreatePointDTO> lst = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lst.add(new CreatePointDTO(count * 1.0, count * 1.0));
        }
        return lst;
    }

    public static List<UpdatePointDTO> createUpdatePointList(Long count) {
        List<UpdatePointDTO> lst = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lst.add(new UpdatePointDTO(count, count * 1.0, count * 1.0));
        }
        return lst;
    }

    public static RadiusInfo createRadiusInfo(Long id, Shape shape) {
        return new RadiusInfo(id, id * 1.0, shape);
    }

    public static Shape createRectangle(Long id) {
        Shape rect = new Shape();
        rect.setShapeId(id);
        List<Point> points = new ArrayList<>();
        points.add(new Point(1L, 0.0, 2.0, rect));
        points.add(new Point(2L, 2.0, 2.0, rect));
        points.add(new Point(3L, 2.0, 0.0, rect));
        points.add(new Point(4L, 0.0, 0.0, rect));
        rect.setPoints(points);
        return rect;
    }

    public static Shape createCircle(Long id) {
        Shape circle = new Shape();
        circle.setRadiusInfo(new RadiusInfo(id, 1.0, circle));
        circle.setPoints(Collections.singletonList(new Point(1L, 1.0, 1.0, circle)));
        return circle;
    }
}
