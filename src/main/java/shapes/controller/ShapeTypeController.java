package shapes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.domain.ShapeType;
import shapes.services.ShapeTypeService;

import java.util.List;

@RestController
@RequestMapping("/shapeType")
public class ShapeTypeController {
    private final ShapeTypeService shapeTypeService;

    @Autowired
    public ShapeTypeController(ShapeTypeService shapeTypeService) {
        this.shapeTypeService = shapeTypeService;
    }

    @GetMapping("/getAll")
    public List<ShapeType> getAll() {
        return shapeTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ShapeType getShapeType(@PathVariable("id") int id) {
        return shapeTypeService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createShapeType(@RequestBody ShapeType shapeType) {
        shapeTypeService.save(shapeType);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateShapeType(@RequestBody ShapeType shapeType) {
        shapeTypeService.save(shapeType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShapeType(@PathVariable("id") int id) {
        shapeTypeService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
