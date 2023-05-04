package shapes.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shapes.exceptions.NotFoundException;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
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

    @ApiOperation(
            value = "Получение всех фигур",
            notes = "Используется для получения всех фигур из базы данных"
    )
    @ApiResponse(code = 200, message = "OK")
    @GetMapping("/all")
    public ResponseEntity<List<ShapeDTO>> getAll() {
        return shapesService.getAll();
    }

    @ApiOperation(
            value = "Получение фигуры",
            notes = "Используется для получения фигуры по id из базы данных"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ShapeDTO> getById(@PathVariable("id") long id) throws NotFoundException {
        return shapesService.getById(id);
    }

    @ApiOperation(
            value = "Создание фигуры",
            notes = "Используется для создания фигуры и сохранения её в базу данных"
    )
    @ApiResponse(code = 201, message = "Фигура создана")
    @PostMapping()
    public ResponseEntity<HttpStatus> createShape(@RequestBody CreateShapeDTO shape) {
        return shapesService.createShape(shape);
    }

    @ApiOperation(
            value = "Обновление фигуры",
            notes = "Используется для обновления фигуры в базе данных"
    )
    @ApiResponse(code = 200, message = "OK")
    @PutMapping()
    public ResponseEntity<HttpStatus> updateShape(@RequestBody UpdateShapeDTO shape) {
        return shapesService.updateShape(shape);
    }

    @ApiOperation(
            value = "Удаление фигуры",
            notes = "Используется для удаления фигуры по id из базы данных"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Фигура удалена"),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteShape(@PathVariable("id") long id) throws NotFoundException {
        return shapesService.deleteById(id);
    }

    @ApiOperation(
            value = "Расчет площади фигуры",
            notes = "Используется для расчета площади фигуры"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = ""),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @GetMapping("/area/{id}")
    public ResponseEntity<CalculatedAreaDTO> calculateArea(@PathVariable("id") long id) throws NotFoundException {
        return shapesService.calculateArea(id);
    }

    @ApiOperation(
            value = "Поворот фигуры",
            notes = "Используется для поворота фигуры относительно её центра"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = ""),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @PostMapping("/roll")
    public ResponseEntity<ShapeDTO> roll(@RequestBody RollDTO rollDTO) throws NotFoundException {
        return shapesService.roll(rollDTO);
    }

    @ApiOperation(
            value = "Перемещение фигуры",
            notes = "Используется для перемещение фигуры относительно её центра"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = ""),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @PostMapping("/move")
    public ResponseEntity<ShapeDTO> move(@RequestBody MoveDTO moveDTO) throws NotFoundException {
        return shapesService.move(moveDTO);
    }

    @ApiOperation(
            value = "Масштабирование фигуры",
            notes = "Используется для изменения масштаба фигуры относительно её центра"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = ""),
                    @ApiResponse(code = 404, message = "Фигура не найдена")
            }
    )
    @PostMapping("/scale")
    public ResponseEntity<ShapeDTO> scale(@RequestBody ScaleDTO scaleDTO) throws NotFoundException {
        return shapesService.scale(scaleDTO);
    }
}
