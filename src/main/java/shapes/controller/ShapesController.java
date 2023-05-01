package shapes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.domain.Shape;
import shapes.services.ShapesService;

import java.util.List;

@RestController
@RequestMapping("/shapes")
public class ShapesController {
    private final ShapesService shapesService;

    @Autowired
    public ShapesController(ShapesService shapesService) {
        this.shapesService = shapesService;
    }

    @GetMapping("/all")
    public List<Shape> getAll() {
        return shapesService.getAll();
    }

    @GetMapping("/{id}")
    public Shape getById(@PathVariable("id") int id) {
        return shapesService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createShape(@RequestBody Shape shape) {
        shapesService.save(shape);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateShape(@RequestBody Shape shape) {
        shapesService.save(shape);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShape(@PathVariable("id") int id) {
        shapesService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
