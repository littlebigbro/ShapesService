package shapes.mappers;

import org.junit.jupiter.api.Test;
import shapes.models.Point;
import shapes.models.Shape;
import shapes.models.ShapeType;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static shapes.utils.TestUtils.*;

public class ShapeMapperTest {
    private final ShapeMapper shapeMapper = new ShapeMapperImpl(
            new PointListMapperImpl(new PointMapperImpl()),
            new ShapeTypeMapperImpl(),
            new RadiusInfoMapperImpl()
    );

    @Test
    public void mapNullShapeToShapeDTOTest() {
        ShapeDTO shapeDTO = shapeMapper.mapToShapeDTO(null);
        assertNull(shapeDTO);
    }

    @Test
    public void mapShapeWithNullParamsToShapeDTOTest() {
        Shape shape = new Shape(
                1L, LocalDateTime.now(), LocalDateTime.now(),
                null, null, null
        );

        ShapeDTO dto = shapeMapper.mapToShapeDTO(shape);

        assertEquals(shape.getShapeId(), dto.getShapeId());
        assertEquals(shape.getCreated(), dto.getCreated());
        assertEquals(shape.getUpdated(), dto.getUpdated());

        assertNull(dto.getShapeType());
        assertNull(dto.getRadiusInfo());
        assertNull(dto.getPoints());
    }

    @Test
    public void mapShapeToShapeDTOTest() {
        ShapeType shapeType = createShapeType(1L, 1);
        Shape shape = new Shape(
                1L, LocalDateTime.now(), LocalDateTime.now(),
                shapeType, null, null
        );
        shape.setRadiusInfo(createRadiusInfo(1L, shape));
        shape.setPoints(createPointList(2L, shape));

        ShapeDTO dto = shapeMapper.mapToShapeDTO(shape);
        // check shape fields
        assertEquals(shape.getShapeId(), dto.getShapeId());
        assertEquals(shape.getCreated(), dto.getCreated());
        assertEquals(shape.getUpdated(), dto.getUpdated());
        // check shapeType
        assertEquals(shape.getShapeType().getShapeTypeId(), dto.getShapeType().getShapeTypeId());
        // check radiusInfo
        assertEquals(shape.getRadiusInfo().getRadiusInfoId(), dto.getRadiusInfo().getRadiusInfoId());
        assertEquals(shape.getRadiusInfo().getRadius(), dto.getRadiusInfo().getRadius());
        // check points
        assertEquals(shape.getPoints().size(), dto.getPoints().size());
        for (int i = 0; i < shape.getPoints().size(); i++) {
            Point point = shape.getPoints().get(i);
            PointDTO pointDTO = dto.getPoints().get(i);
            assertEquals(point.getPointId(), pointDTO.getPointId());
            assertEquals(point.getX(), pointDTO.getX());
            assertEquals(point.getY(), pointDTO.getY());
        }
    }

    @Test
    public void mapNullCreateShapeDTOToShapeTest() {
        Shape shape = shapeMapper.mapToShape((CreateShapeDTO) null);
        assertNull(shape);
    }

    @Test
    public void mapCreateShapeDTOToShapeTest() {
        ShapeTypeForShapeDTO shapeTypeDTO = new ShapeTypeForShapeDTO(1L);
        CreateRadiusInfoDTO radiusInfoDTO = new CreateRadiusInfoDTO(1.0);
        List<CreatePointDTO> pointsDTO = createCreatePointList(1);
        CreateShapeDTO dto = new CreateShapeDTO();
        dto.setShapeType(shapeTypeDTO);
        dto.setRadiusInfo(radiusInfoDTO);
        dto.setPoints(pointsDTO);

        Shape shape = shapeMapper.mapToShape(dto);
        // check shapeType
        assertEquals(shapeTypeDTO.getShapeTypeId(), shape.getShapeType().getShapeTypeId());
        // check radiusInfo
        assertEquals(radiusInfoDTO.getRadius(), shape.getRadiusInfo().getRadius());
        // check points
        assertEquals(pointsDTO.size(), shape.getPoints().size());
        for (int i = 0; i < pointsDTO.size(); i++) {
            Point point = shape.getPoints().get(i);
            CreatePointDTO pointDTO = dto.getPoints().get(i);
            assertEquals(point.getX(), pointDTO.getX());
            assertEquals(point.getY(), pointDTO.getY());
        }
    }

    @Test
    public void mapNullUpdateShapeDTOToShapeTest() {
        Shape shape = shapeMapper.mapToShape((UpdateShapeDTO) null);
        assertNull(shape);
    }


    @Test
    public void mapUpdateShapeDTOToShapeTest() {
        ShapeTypeForShapeDTO shapeTypeDTO = new ShapeTypeForShapeDTO(1L);
        UpdateRadiusInfoDTO radiusInfoDTO = new UpdateRadiusInfoDTO(1L, 1.0);
        List<UpdatePointDTO> pointsDTO = createUpdatePointList(1L);
        UpdateShapeDTO dto = new UpdateShapeDTO();
        dto.setShapeType(shapeTypeDTO);
        dto.setRadiusInfo(radiusInfoDTO);
        dto.setPoints(pointsDTO);

        Shape shape = shapeMapper.mapToShape(dto);
        // check shapeType
        assertEquals(shapeTypeDTO.getShapeTypeId(), shape.getShapeType().getShapeTypeId());
        // check radiusInfo
        assertEquals(radiusInfoDTO.getRadiusInfoId(), shape.getRadiusInfo().getRadiusInfoId());
        assertEquals(radiusInfoDTO.getRadius(), shape.getRadiusInfo().getRadius());
        // check points
        assertEquals(pointsDTO.size(), shape.getPoints().size());
        for (int i = 0; i < pointsDTO.size(); i++) {
            Point point = shape.getPoints().get(i);
            UpdatePointDTO pointDTO = dto.getPoints().get(i);
            assertEquals(point.getPointId(), pointDTO.getPointId());
            assertEquals(point.getX(), pointDTO.getX());
            assertEquals(point.getY(), pointDTO.getY());
        }
    }
}