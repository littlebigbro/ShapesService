package shapes.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.services.ShapeTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shapeType")
public class ShapeTypeController {
    private final ShapeTypeService shapeTypeService;

    @GetMapping("/all")
    public List<ShapeTypeDTO> getAll() {
        return shapeTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ShapeTypeDTO getShapeType(@PathVariable("id") int id) {
        return shapeTypeService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createShapeType(@RequestBody CreateShapeTypeDTO shapeType) {
        shapeTypeService.createShapeType(shapeType);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> updateShapeType(@RequestBody UpdateShapeTypeDTO shapeType) {
        shapeTypeService.updateShapeType(shapeType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShapeType(@PathVariable("id") int id) {
        shapeTypeService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
