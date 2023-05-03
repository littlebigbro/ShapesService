package shapes.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;
import shapes.services.ShapeTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shapeType")
public class ShapeTypeController implements BaseController {
    private final ShapeTypeService shapeTypeService;

    @ApiOperation(
            value = "Получение всех типов фигур",
            notes = "Используется для получения всех типов фигур из базы данных")
    @ApiResponse(code = 200, message = "ОК")
    @GetMapping("/all")
    public ResponseEntity<List<ShapeTypeDTO>> getAll() {
        return shapeTypeService.getAll();
    }

    @ApiOperation(
            value = "Получение типа фигуры",
            notes = "Используется для получения типа фигуры по id из базы данных"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Тип фигуры не найден")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ShapeTypeDTO> getShapeType(@PathVariable("id") int id) throws NotFoundException {
        return shapeTypeService.getById(id);
    }

    @ApiOperation(
            value = "Создание типа фигуры",
            notes = "Используется для создания типа фигуры и сохранения её в базу данных"
    )
    @ApiResponse(code = 201, message = "Тип фигуры создан")
    @PostMapping()
    public ResponseEntity<HttpStatus> createShapeType(@RequestBody CreateShapeTypeDTO shapeType) {
        shapeTypeService.createShapeType(shapeType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Обновление типа фигуры",
            notes = "Используется для обновления типа фигуры в базе данных"
    )
    @ApiResponse(code = 200, message = "OK")
    @PutMapping()
    public ResponseEntity<HttpStatus> updateShapeType(@RequestBody UpdateShapeTypeDTO shapeType) {
        shapeTypeService.updateShapeType(shapeType);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Удаление типа фигуры",
            notes = "Используется для удаления типа фигуры по id из базы данных"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Тип фигуры удален"),
                    @ApiResponse(code = 404, message = "Тип фигуры не найден")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShapeType(@PathVariable("id") int id) throws NotFoundException {
        shapeTypeService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
