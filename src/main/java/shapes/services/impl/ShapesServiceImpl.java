package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.exceptions.NotFoundException;
import shapes.exceptions.ShapeValidationException;
import shapes.mappers.ShapeMapper;
import shapes.models.Point;
import shapes.models.Shape;
import shapes.models.ShapeType;
import shapes.models.dto.action.CalculatedAreaDTO;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.repositories.ShapeTypeRepository;
import shapes.repositories.ShapesRepository;
import shapes.responses.ValidationErrorResponse;
import shapes.services.ShapesService;
import shapes.utils.ClockwiseComparator;
import shapes.utils.PointsByIdComparator;

import java.util.List;
import java.util.stream.Collectors;

import static shapes.utils.Utils.*;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapesServiceImpl implements ShapesService {
    private final ShapesRepository shapesRepository;
    private final ShapeTypeRepository shapeTypeRepository;
    private final ShapeMapper mapper;

    @Override
    public ResponseEntity<List<ShapeDTO>> getAll() {
        List<Shape> shapes = shapesRepository.findAll();
        return new ResponseEntity<>(
                shapes.stream()
                        .map(mapper::mapToShapeDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> getById(long id) {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, id));
        ShapeDTO shapeDTO = mapper.mapToShapeDTO(shape);
        return new ResponseEntity<>(shapeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> createShape(CreateShapeDTO shapeDTO) throws ShapeValidationException {
        Shape shape = mapper.mapToShape(shapeDTO);
        checkShape(shape);
        shapesRepository.save(shape);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> updateShape(UpdateShapeDTO shapeDTO) throws ShapeValidationException {
        Shape receivedShape = mapper.mapToShape(shapeDTO);
        Long shapeId = receivedShape.getShapeId();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, shapeId));
        updateShapeByReceivedShape(shape, receivedShape);
        checkShape(shape);
        shapesRepository.save(shape);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateShapeByReceivedShape(Shape shape, Shape receivedShape) {
        if (receivedShape.getShapeType() != null) {
            shape.setShapeType(receivedShape.getShapeType());
        }
        if (receivedShape.getPoints() != null) {
            shape.setPoints(receivedShape.getPoints());
        }
        if (receivedShape.getRadiusInfo() != null) {
            shape.setRadiusInfo(receivedShape.getRadiusInfo());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> deleteById(long id) {
        if (!shapesRepository.existsById(id)) {
            throw new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, id);
        }
        shapesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CalculatedAreaDTO> calculateArea(long id) {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, id));
        List<Point> points = shape.getPoints();
        Double resultArea = 0.0;
        if (collectionIsNotEmpty(points)) {
            int pointsSize = points.size();
            if (pointsSize == 1) {
                // значит круг
                Double radius = shape.getRadiusInfo().getRadius();
                resultArea = roundDouble(Math.PI * radius * radius);
            } else {
                //Формула площади Гаусса
                points.sort(new ClockwiseComparator(calculateCenter(points)));
                Point firstPoint = points.get(0);
                Point lastPoint = points.get(pointsSize - 1);
                double a = 0;
                double b = lastPoint.getX() * firstPoint.getY();
                double c = 0;
                double d = firstPoint.getX() * lastPoint.getY();

                for (int i = 0; i < pointsSize - 1; i++) {
                    Point pointX = points.get(i);
                    Point pointY = points.get(i + 1);
                    a += pointX.getX() * pointY.getY();
                }
                for (int i = 0; i < pointsSize - 1; i++) {
                    Point pointX = points.get(i + 1);
                    Point pointY = points.get(i);
                    c += pointX.getX() * pointY.getY();
                }
                resultArea = roundDouble(Math.abs((a + b - c - d) / 2));
            }
        }
        return new ResponseEntity<>(new CalculatedAreaDTO(resultArea), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> roll(RollDTO rollDTO) {
        Long shapeId = rollDTO.getShapeId();
        Double angle = rollDTO.getAngle();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, shapeId));
        List<Point> points = shape.getPoints();
        if (points.size() > 1) {
            Point center = calculateCenter(points);
            points.sort(new ClockwiseComparator(center));
            double radian = Math.toRadians(angle);
            Double centerX = center.getX();
            Double centerY = center.getY();
            double cos = roundDouble(Math.cos(radian));
            double sin = roundDouble(Math.sin(radian));

            for (Point point : points) {
                Double x = point.getX();
                Double y = point.getY();
                double tempX = centerX + roundDouble((x - centerX) * cos) - roundDouble((y - centerY) * sin);
                double tempY = centerY + roundDouble((y - centerY) * cos) + roundDouble((x - centerX) * sin);
                point.setX(tempX);
                point.setY(tempY);
            }
            points.sort(new PointsByIdComparator());
        }
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> move(MoveDTO moveDTO) {
        Long shapeId = moveDTO.getShapeId();
        Double x = moveDTO.getX();
        Double y = moveDTO.getY();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, shapeId));
        List<Point> points = shape.getPoints();
        Point center = calculateCenter(points);
        double dX = x - center.getX();
        double dY = y - center.getY();
        for (Point point : points) {
            point.setX(point.getX() + dX);
            point.setY(point.getY() + dY);
        }
        points.sort(new PointsByIdComparator());
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> scale(ScaleDTO scaleDTO) {
        Long shapeId = scaleDTO.getShapeId();
        Double scaleFactor = scaleDTO.getScaleFactor();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_NOT_FOUND_MSG, shapeId));
        List<Point> points = shape.getPoints();
        if (points.size() == 1) {
            Double radius = shape.getRadiusInfo().getRadius();
            Double newRadius = roundDouble(radius * scaleFactor);
            shape.getRadiusInfo().setRadius(newRadius);
        } else {
            Point center = calculateCenter(points);
            points.sort(new ClockwiseComparator(center));
            Double centerX = center.getX();
            Double centerY = center.getY();
            for (Point point : points) {
                double tempX = centerX + roundDouble(scaleFactor * (point.getX() - centerX));
                double tempY = centerY + roundDouble(scaleFactor * (point.getY() - centerY));
                point.setX(tempX);
                point.setY(tempY);
            }
            points.sort(new PointsByIdComparator());
        }
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    private void checkShape(Shape shape) throws ShapeValidationException {
        Long shapeTypeId = shape.getShapeType().getShapeTypeId();
        ShapeType shapeType = shapeTypeRepository.findById(shapeTypeId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.SHAPE_TYPE_NOT_FOUND_MSG, shapeTypeId));
        List<Point> shapePoints = shape.getPoints();
        if (collectionIsNotEmpty(shapePoints)) {
            int shapePointsCount = shapePoints.size();
            if (shapeType.getPointsCount() != shapePointsCount) {
                String message = String.format("Количество точек [%s] переданных в json не соответствует выбранному типу фигуры [%s]",
                        shapePointsCount, shapeType.getName());
                throw new ShapeValidationException(message);
            }
        }
    }
}