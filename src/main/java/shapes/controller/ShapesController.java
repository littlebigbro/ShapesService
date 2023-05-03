package shapes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.services.ShapesService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shapes")
public class ShapesController implements BaseController {
    private final ShapesService shapesService;

    @GetMapping("/all")
    public ResponseEntity<List<ShapeDTO>> getAll() {
        return shapesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShapeDTO> getById(@PathVariable("id") int id) throws NotFoundException {
        return shapesService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createShape(@RequestBody CreateShapeDTO shape) {
        return shapesService.createShape(shape);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateShape(@RequestBody UpdateShapeDTO shape) {
        return shapesService.updateShape(shape);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShape(@PathVariable("id") int id) throws NotFoundException{
        return shapesService.deleteById(id);
    }
}
