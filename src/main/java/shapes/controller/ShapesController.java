package shapes.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shapes.exceptions.ShapeValidationException;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.responses.ValidationErrorResponse;
import shapes.services.ShapesService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shapes")
@Api(value = "Контроллер фигур")
public class ShapesController implements BaseController {
    private final ShapesService shapesService;

    @ApiOperation(
            value = "Получение всех фигур",
            notes = "Используется для получения всех фигур из базы данных"
    )
    @ApiResponse(code = 200, message = "OK")
    @GetMapping(value = "/all")
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<ShapeDTO> getById(@PathVariable("id") long id) {
        return shapesService.getById(id);
    }

    @ApiOperation(
            value = "Создание фигуры",
            notes = "Используется для создания фигуры и сохранения её в базу данных"
    )
    @ApiResponse(code = 201, message = "Фигура создана")
    @PostMapping
    public ResponseEntity<ValidationErrorResponse> createShape(@RequestBody @Valid CreateShapeDTO shape, BindingResult bindingResult) throws ShapeValidationException {
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse response = new ValidationErrorResponse(bindingResult.getFieldErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return shapesService.createShape(shape);
    }

    @ApiOperation(
            value = "Обновление фигуры",
            notes = "Используется для обновления фигуры в базе данных"
    )
    @ApiResponse(code = 200, message = "OK")
    @PutMapping
    public ResponseEntity<ValidationErrorResponse> updateShape(@RequestBody @Valid UpdateShapeDTO shape, BindingResult bindingResult) throws ShapeValidationException {
        if (bindingResult.hasErrors()) {
            ValidationErrorResponse response = new ValidationErrorResponse(bindingResult.getFieldErrors());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
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
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteShape(@PathVariable("id") long id) {
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
    @GetMapping(value = "/area/{id}")
    public ResponseEntity<CalculatedAreaDTO> calculateArea(@PathVariable("id") long id) {
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
    @PostMapping(value = "/roll")
    public ResponseEntity<ShapeDTO> roll(@RequestBody RollDTO rollDTO) {
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
    @PostMapping(value = "/move")
    public ResponseEntity<ShapeDTO> move(@RequestBody MoveDTO moveDTO) {
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
    @PostMapping(value = "/scale")
    public ResponseEntity<ShapeDTO> scale(@RequestBody ScaleDTO scaleDTO) {
        return shapesService.scale(scaleDTO);
    }
}
