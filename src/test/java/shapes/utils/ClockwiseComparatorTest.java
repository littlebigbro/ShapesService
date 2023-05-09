package shapes.utils;

import org.junit.jupiter.api.Test;
import shapes.models.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClockwiseComparatorTest {

    @Test
    public void compareTest() {
        Point center = new Point(0L, 0.0, 0.0, null);
        ClockwiseComparator comparator = new ClockwiseComparator(center);
        Point pointA = new Point(1L, 0.0, 1.0, null);
        Point pointB = new Point(2L, 1.0, 0.0, null);
        Point pointC = new Point(3L, 0.0, 2.0, null);
        Point pointD = new Point(4L, -1.0, 0.5, null);

        assertEquals(-1, comparator.compare(pointA, pointB));
        assertEquals(0, comparator.compare(pointA, pointC));
        assertEquals(1, comparator.compare(pointA, pointD));
    }
}