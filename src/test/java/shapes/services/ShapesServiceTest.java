package shapes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shapes.exceptions.NotFoundException;
import shapes.exceptions.ShapeValidationException;
import shapes.mappers.*;
import shapes.models.Point;
import shapes.models.Shape;
import shapes.models.ShapeType;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.repositories.ShapesRepository;
import shapes.responses.ValidationErrorResponse;
import shapes.services.impl.ShapesServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shapes.utils.TestUtils.createCircle;
import static shapes.utils.TestUtils.createRectangle;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        ShapeMapperImpl.class,
        PointListMapperImpl.class,
        ShapeTypeMapperImpl.class,
        RadiusInfoMapperImpl.class,
        PointMapperImpl.class
})
public class ShapesServiceTest {

    @Mock
    private ShapeTypeRepository shapeTypeRepository;

    @Mock
    private ShapesRepository shapesRepository;

    @Autowired
    private ShapeMapper shapeMapper;

    private ShapesService shapeService;

    @BeforeEach
    public void init() {
        shapeTypeRepository = mock(ShapeTypeRepository.class);
        shapesRepository = mock(ShapesRepository.class);
        shapeService = new ShapesServiceImpl(shapesRepository, shapeTypeRepository, shapeMapper);
    }

    @Test
    public void getAllTest() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Shape());
        shapes.add(new Shape());

        when(shapesRepository.findAll()).thenReturn(shapes);

        ResponseEntity<List<ShapeDTO>> response = shapeService.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getByIdSuccessTest() {
        Long id = 1L;
        Shape shape = new Shape(id, null, null, null, null, null);
        when(shapesRepository.findById(id)).thenReturn(Optional.of(shape));

        ResponseEntity<ShapeDTO> response = shapeService.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getShapeId());
    }

    @Test
    public void getByIdFailTest() {
        long id = 1L;
        when(shapesRepository.findById(id)).thenThrow(new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, id));
        assertThrows(NotFoundException.class, () -> shapeService.getById(id));
    }

    @Test
    public void createShapeSuccessTest() {
        CreateShapeDTO dto = new CreateShapeDTO();
        dto.setShapeType(new ShapeTypeForShapeDTO(1L));
        dto.setPoints(Collections.singletonList(new CreatePointDTO(1.0, 1.0)));

        ShapeType shapeType = new ShapeType(1L, "", "", null, null, 1);
        when(shapeTypeRepository.findById(1L)).thenReturn(Optional.of(shapeType));

        ResponseEntity<ValidationErrorResponse> response = shapeService.createShape(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void createShapeFailTest() {
        CreateShapeDTO dto = new CreateShapeDTO();
        dto.setShapeType(new ShapeTypeForShapeDTO(1L));
        dto.setPoints(Collections.singletonList(new CreatePointDTO(1.0, 1.0)));

        when(shapeTypeRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> shapeService.createShape(dto));
    }

    @Test
    public void validationFailTest() {
        CreateShapeDTO dto = new CreateShapeDTO();
        dto.setShapeType(new ShapeTypeForShapeDTO(1L));
        dto.setPoints(Collections.singletonList(new CreatePointDTO(1.0, 1.0)));

        ShapeType shapeType = new ShapeType(1L, "", "", null, null, 2);
        when(shapeTypeRepository.findById(1L)).thenReturn(Optional.of(shapeType));
        assertThrows(ShapeValidationException.class, () -> shapeService.createShape(dto));
    }

    @Test
    public void updateShapeTypeTest() {
        UpdateShapeDTO dto = new UpdateShapeDTO(
                1L,
                new ShapeTypeForShapeDTO(1L),
                Collections.singletonList(new UpdatePointDTO(1L, 1.0, 1.0)),
                new UpdateRadiusInfoDTO(1L, 1.0));


        Shape shape = new Shape();
        ShapeType shapeType = new ShapeType(1L, "", "", null, null, 1);
        when(shapesRepository.findById(1L)).thenReturn(Optional.of(shape));
        when(shapeTypeRepository.findById(1L)).thenReturn(Optional.of(shapeType));

        ResponseEntity<ValidationErrorResponse> response = shapeService.updateShape(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteByIdSuccessTest() {
        long id = 1L;

        when(shapesRepository.existsById(id)).thenReturn(true);

        ResponseEntity<HttpStatus> response = shapeService.deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteByIdFailTest() {
        long id = 1L;

        when(shapesRepository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> shapeService.deleteById(id));
    }

    @Test
    public void calculateCircleAreaTest() {
        Long id = 1L;
        Shape shape = createCircle(id);
        when(shapesRepository.findById(id)).thenReturn(Optional.of(shape));

        ResponseEntity<CalculatedAreaDTO> response = shapeService.calculateArea(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3.14, response.getBody().getShapeArea());
    }

    @Test
    public void calculateRectangleAreaTest() {
        long id = 1L;
        Shape shape = createRectangle(id);
        when(shapesRepository.findById(id)).thenReturn(Optional.of(shape));

        ResponseEntity<CalculatedAreaDTO> response = shapeService.calculateArea(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(4.00, response.getBody().getShapeArea());
    }

    @Test
    public void rollTest() {
        RollDTO dto = new RollDTO(1L, 90.0);

        Shape rectangle = createRectangle(1L);
        List<Point> sourcePoints = rectangle.getPoints();
        when(shapesRepository.findById(1L)).thenReturn(Optional.of(createRectangle(1L)));

        ResponseEntity<ShapeDTO> response = shapeService.roll(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ShapeDTO shapeDTO = response.getBody();
        assertNotNull(shapeDTO);

        List<PointDTO> points = shapeDTO.getPoints();
        assertNotNull(points);
        assertEquals(sourcePoints.size(), points.size());

        assertEquals(sourcePoints.get(3).getX(), points.get(0).getX());
        assertEquals(sourcePoints.get(3).getY(), points.get(0).getY());
        assertEquals(sourcePoints.get(0).getX(), points.get(1).getX());
        assertEquals(sourcePoints.get(0).getY(), points.get(1).getY());
        assertEquals(sourcePoints.get(1).getX(), points.get(2).getX());
        assertEquals(sourcePoints.get(1).getY(), points.get(2).getY());
        assertEquals(sourcePoints.get(2).getX(), points.get(3).getX());
        assertEquals(sourcePoints.get(2).getY(), points.get(3).getY());
    }

    @Test
    public void moveTest() {
        MoveDTO dto = new MoveDTO(1L, 2.0, 1.0);

        when(shapesRepository.findById(1L)).thenReturn(Optional.of(createRectangle(1L)));

        ResponseEntity<ShapeDTO> response = shapeService.move(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ShapeDTO shapeDTO = response.getBody();
        assertNotNull(shapeDTO);

        List<PointDTO> points = shapeDTO.getPoints();
        assertNotNull(points);
        assertEquals(1.0, points.get(0).getX());
        assertEquals(2.0, points.get(0).getY());
        assertEquals(3.0, points.get(1).getX());
        assertEquals(2.0, points.get(1).getY());
        assertEquals(3.0, points.get(2).getX());
        assertEquals(0.0, points.get(2).getY());
        assertEquals(1.0, points.get(3).getX());
        assertEquals(0.0, points.get(3).getY());
    }

    @Test
    public void scaleCircleTest() {
        ScaleDTO dto = new ScaleDTO(1L, 2.0);

        when(shapesRepository.findById(1L)).thenReturn(Optional.of(createCircle(1L)));

        ResponseEntity<ShapeDTO> response = shapeService.scale(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ShapeDTO shapeDTO = response.getBody();
        assertNotNull(shapeDTO);
        assertEquals(2.0, shapeDTO.getRadiusInfo().getRadius());
    }

    @Test
    public void scaleRectangleTest() {
        ScaleDTO dto = new ScaleDTO(1L, 2.0);

        when(shapesRepository.findById(1L)).thenReturn(Optional.of(createRectangle(1L)));

        ResponseEntity<ShapeDTO> response = shapeService.scale(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ShapeDTO shapeDTO = response.getBody();
        assertNotNull(shapeDTO);

        List<PointDTO> points = shapeDTO.getPoints();
        assertNotNull(points);
        assertEquals(-1.0, points.get(0).getX());
        assertEquals(3.0, points.get(0).getY());
        assertEquals(3.0, points.get(1).getX());
        assertEquals(3.0, points.get(1).getY());
        assertEquals(3.0, points.get(2).getX());
        assertEquals(-1.0, points.get(2).getY());
        assertEquals(-1.0, points.get(3).getX());
        assertEquals(-1.0, points.get(3).getY());
    }
}