package shapes.utils;


import org.junit.jupiter.api.Test;
import shapes.models.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class UtilsTest {

    @Test
    public void collectionIsNotEmptyTest() {
        assertFalse(Utils.collectionIsNotEmpty(null));
        assertFalse(Utils.collectionIsNotEmpty(new ArrayList<>()));
    }

    @Test
    public void collectionIsEmptyTest() {
        assertTrue(Utils.collectionIsEmpty(null));
        assertTrue(Utils.collectionIsEmpty(new ArrayList<>()));
    }

    @Test
    public void roundDoubleTest() {
        Double testValue_1 = new Double("0.015");
        Double testValue_2 = new Double("0.014");

        assertEquals("0.02", Utils.roundDouble(testValue_1).toString());
        assertEquals("0.01", Utils.roundDouble(testValue_2).toString());

    }

    @Test
    public void calculateCenterOnePointTest() {
        Point point = new Point(0L, 1.0, 1.0, null);
        Point falsePoint = new Point(1L, 2.0, 1.0, null);
        Set<Point> pointSet = Collections.singleton(point);
        Point center = Utils.calculateCenter(new ArrayList<>(pointSet));

        assertEquals(point, center);
        assertNotEquals(falsePoint, center);
    }

    @Test
    public void calculateCenterManyPointTest() {
        Point pointA = new Point(1L, 0.0, 0.0, null);
        Point pointB = new Point(2L, 0.0, 2.0, null);
        Point pointC = new Point(3L, 2.0, 2.0, null);
        Point pointD = new Point(4L, 2.0, 0.0, null);
        List<Point> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);
        Point center = new Point(5L, 1.0, 1.0, null);

        assertEquals(center, Utils.calculateCenter(points));
    }
}