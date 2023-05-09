package shapes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shapes.exceptions.NotFoundException;
import shapes.models.ShapeType;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.responses.ValidationErrorResponse;
import shapes.services.impl.ShapeTypeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShapeTypeServiceTest {

    @Mock
    private ShapeTypeRepository repository;

    private ShapeTypeService shapeTypeService;

    @BeforeEach
    public void init() {
        repository = mock(ShapeTypeRepository.class);
        shapeTypeService = new ShapeTypeServiceImpl(repository);
    }

    @Test
    public void getAllTest() {
        List<ShapeType> shapeTypes = new ArrayList<>();
        shapeTypes.add(new ShapeType());
        shapeTypes.add(new ShapeType());

        when(repository.findAll()).thenReturn(shapeTypes);

        ResponseEntity<List<ShapeTypeDTO>> response = shapeTypeService.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getByIdSuccessTest() {
        Long id = 1L;
        ShapeType shapeType = new ShapeType(id, null, null, null, null, null);
        when(repository.findById(id)).thenReturn(Optional.of(shapeType));

        ResponseEntity<ShapeTypeDTO> response = shapeTypeService.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getShapeTypeId());
    }


    @Test
    public void getByIdFailTest() {
        long id = 1L;
        when(repository.findById(id)).thenThrow(new NotFoundException(NotFoundException.SHAPE_TYPE_NOT_FOUND_MSG, id));
        assertThrows(NotFoundException.class, () -> shapeTypeService.getById(id));
    }

    @Test
    public void createShapeTypeTest() {
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO();
        ResponseEntity<ValidationErrorResponse> response = shapeTypeService.createShapeType(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void updateShapeTypeTest() {
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(1L, "systemName", "name", 1);
        ShapeType shapeType = new ShapeType();

        when(repository.findById(1L)).thenReturn(Optional.of(shapeType));

        ResponseEntity<ValidationErrorResponse> response = shapeTypeService.updateShapeType(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteByIdSuccessTest() {
        long id = 1L;

        when(repository.existsById(id)).thenReturn(true);

        ResponseEntity<HttpStatus> response = shapeTypeService.deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


    @Test
    public void deleteByIdFailTest() {
        long id = 1L;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> shapeTypeService.deleteById(id));
    }
}