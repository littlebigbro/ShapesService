package shapes.mappers;

import org.junit.jupiter.api.Test;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static shapes.utils.TestUtils.createShapeType;

public class ShapeTypeMapperTest {
    private final ShapeTypeMapper shapeTypeMapper = ShapeTypeMapper.MAPPER;

    @Test
    public void mapNullShapeTypeToShapeTypeDTOTest() {
        ShapeTypeDTO shapeTypeDTO = shapeTypeMapper.mapToShapeTypeDTO(null);
        assertNull(shapeTypeDTO);
    }

    @Test
    public void mapShapeTypeToShapeTypeDTOTest() {
        ShapeType shapeType = createShapeType(1L, 1);
        ShapeTypeDTO dto = shapeTypeMapper.mapToShapeTypeDTO(shapeType);

        assertEquals(shapeType.getShapeTypeId(), dto.getShapeTypeId());
        assertEquals(shapeType.getSystemName(), dto.getSystemName());
        assertEquals(shapeType.getName(), dto.getName());
        assertEquals(shapeType.getCreated(), dto.getCreated());
        assertEquals(shapeType.getUpdated(), dto.getUpdated());
        assertEquals(shapeType.getPointsCount(), dto.getPointsCount());
    }

    @Test
    public void mapNullUpdateShapeTypeDTOToShapeTypeTest() {
        ShapeType shapeType = shapeTypeMapper.mapToShapeType((UpdateShapeTypeDTO) null);
        assertNull(shapeType);
    }

    @Test
    public void mapUpdateShapeTypeDTOToShapeTypeTest() {
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(1L, "SystemName", "Name", 1);
        ShapeType shapeType = shapeTypeMapper.mapToShapeType(dto);

        assertEquals(dto.getShapeTypeId(), shapeType.getShapeTypeId());
        assertEquals(dto.getSystemName(), shapeType.getSystemName());
        assertEquals(dto.getName(), shapeType.getName());
        assertEquals(dto.getPointsCount(), shapeType.getPointsCount());
    }

    @Test
    public void mapNullCreateShapeTypeDTOToShapeTypeTest() {
        ShapeType shapeType = shapeTypeMapper.mapToShapeType((CreateShapeTypeDTO) null);
        assertNull(shapeType);
    }

    @Test
    public void mapCreateShapeTypeDTOToShapeTypeTest() {
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO("SystemName", "Name", 1);
        ShapeType shapeType = shapeTypeMapper.mapToShapeType(dto);

        assertEquals(dto.getSystemName(), shapeType.getSystemName());
        assertEquals(dto.getName(), shapeType.getName());
        assertEquals(dto.getPointsCount(), shapeType.getPointsCount());
    }

    @Test
    public void mapNullShapeTypeForShapeDTOToShapeTypeTest() {
        ShapeType shapeType = shapeTypeMapper.mapToShapeType((ShapeTypeForShapeDTO) null);
        assertNull(shapeType);
    }

    @Test
    public void mapShapeTypeForShapeDTOToShapeTypeTest() {
        ShapeTypeForShapeDTO dto = new ShapeTypeForShapeDTO(1L);
        ShapeType shapeType = shapeTypeMapper.mapToShapeType(dto);

        assertEquals(dto.getShapeTypeId(), shapeType.getShapeTypeId());
    }
}