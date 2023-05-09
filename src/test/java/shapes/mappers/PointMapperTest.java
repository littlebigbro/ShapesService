package shapes.mappers;

import org.junit.jupiter.api.Test;
import shapes.models.Point;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PointMapperTest {
    private final PointMapper mapper = PointMapper.MAPPER;

    @Test
    public void mapNullPointToPointDTOTest() {
        PointDTO nullDTO = mapper.mapToPointDTO(null);
        assertNull(nullDTO);
    }

    @Test
    public void mapPointToPointDTOTest() {
        Point point = new Point(1L, 1.0, 1.0, null);
        PointDTO pointDTO = mapper.mapToPointDTO(point);

        assertEquals(point.getPointId(), pointDTO.getPointId());
        assertEquals(point.getX(), pointDTO.getX());
        assertEquals(point.getY(), pointDTO.getY());
    }

    @Test
    public void mapNullCreatePointDTOToPointTest() {
        Point point = mapper.mapToPoint((CreatePointDTO) null);
        assertNull(point);
    }

    @Test
    public void mapCreatePointDTOToPointTest() {
        CreatePointDTO dto = new CreatePointDTO(1.0, 1.0);
        Point point = mapper.mapToPoint(dto);

        assertEquals(dto.getX(), point.getX());
        assertEquals(dto.getY(), point.getY());
    }

    @Test
    public void mapNullUpdatePointDTOToPointTest() {
        Point point = mapper.mapToPoint((UpdatePointDTO) null);
        assertNull(point);
    }


    @Test
    public void mapUpdatePointDTOToPointTest() {
        UpdatePointDTO dto = new UpdatePointDTO(1L, 1.0, 1.0);
        Point point = mapper.mapToPoint(dto);

        assertEquals(dto.getPointId(), point.getPointId());
        assertEquals(dto.getX(), point.getX());
        assertEquals(dto.getY(), point.getY());
    }
}