package shapes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.services.ShapesService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shapes")
public class ShapesController {
    private final ShapesService shapesService;

    @GetMapping("/all")
    public List<ShapeDTO> getAll() {
        return shapesService.getAll();
    }

    @GetMapping("/{id}")
    public ShapeDTO getById(@PathVariable("id") int id) {
        return shapesService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createShape(@RequestBody CreateShapeDTO shape) {
        shapesService.createShape(shape);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateShape(@RequestBody UpdateShapeDTO shape) {
        shapesService.updateShape(shape);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShape(@PathVariable("id") int id) {
        shapesService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
