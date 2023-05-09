package shapes.mappers;

import org.junit.jupiter.api.Test;
import shapes.models.Point;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static shapes.utils.TestUtils.*;

public class PointListMapperTest {
    private final PointListMapper mapper = new PointListMapperImpl(PointMapper.MAPPER);

    @Test
    public void mapNullPointsToPointDTOTest() {
        List<PointDTO> pointDTOS = mapper.mapToPointsDTO(null);
        assertNull(pointDTOS);
    }

    @Test
    public void mapPointsToPointDTOTest() {
        List<Point> points = createPointList(2L, null);
        List<PointDTO> pointDTOS = mapper.mapToPointsDTO(points);
        PointDTO pointDTO1 = pointDTOS.get(0);
        Point pointA = points.get(0);

        assertEquals(pointA.getPointId(), pointDTO1.getPointId());
        assertEquals(pointA.getX(), pointDTO1.getX());
        assertEquals(pointA.getY(), pointDTO1.getY());

        PointDTO pointDTO2 = pointDTOS.get(1);
        Point pointB = points.get(1);

        assertEquals(pointB.getPointId(), pointDTO2.getPointId());
        assertEquals(pointB.getX(), pointDTO2.getX());
        assertEquals(pointB.getY(), pointDTO2.getY());
    }



    @Test
    public void mapNullCreatePointDTOToPointListTest() {
        List<Point> points = mapper.mapToPointList(null);
        assertNull(points);
    }

    @Test
    public void mapCreatePointDTOToPointListTest() {
        List<CreatePointDTO> dtoList = createCreatePointList(2);
        List<Point> points = mapper.mapToPointList(dtoList);

        Point point1 = points.get(0);
        CreatePointDTO dto1 = dtoList.get(0);
        assertEquals(dto1.getX(), point1.getX());
        assertEquals(dto1.getY(), point1.getY());

        Point point2 = points.get(1);
        CreatePointDTO dto2 = dtoList.get(1);
        assertEquals(dto2.getX(), point2.getX());
        assertEquals(dto2.getY(), point2.getY());
    }

    @Test
    public void mapNullUpdatePointDTOToPointListTest() {
        List<Point> points = mapper.toPointList(null);
        assertNull(points);
    }


    @Test
    public void mapUpdatePointDTOToPointListTest() {
        List<UpdatePointDTO> dtoList = createUpdatePointList(2L);
        List<Point> points = mapper.toPointList(dtoList);

        Point point1 = points.get(0);
        UpdatePointDTO dto1 = dtoList.get(0);
        assertEquals(dto1.getPointId(), point1.getPointId());
        assertEquals(dto1.getX(), point1.getX());
        assertEquals(dto1.getY(), point1.getY());

        Point point2 = points.get(1);
        UpdatePointDTO dto2 = dtoList.get(1);
        assertEquals(dto2.getPointId(), point2.getPointId());
        assertEquals(dto2.getX(), point2.getX());
        assertEquals(dto2.getY(), point2.getY());
    }
}