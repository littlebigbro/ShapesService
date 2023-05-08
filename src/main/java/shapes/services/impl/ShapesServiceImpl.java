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
import shapes.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ShapeDTO> getById(long id) throws NotFoundException {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Shape.class));
        ShapeDTO shapeDTO = mapper.mapToShapeDTO(shape);
        return new ResponseEntity<>(shapeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> createShape(CreateShapeDTO shapeDTO) throws NotFoundException, ShapeValidationException {
        Shape shape = mapper.mapToShape(shapeDTO);
        checkShape(shape);
        shapesRepository.save(shape);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<ValidationErrorResponse> updateShape(UpdateShapeDTO shapeDTO) throws NotFoundException, ShapeValidationException {
        Shape receivedShape = mapper.mapToShape(shapeDTO);
        Long shapeId = receivedShape.getShapeId();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(shapeId, Shape.class));
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
    public ResponseEntity<HttpStatus> deleteById(long id) throws NotFoundException {
        if (!shapesRepository.existsById(id)) {
            throw new NotFoundException(id, Shape.class);
        }
        shapesRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CalculatedAreaDTO> calculateArea(long id) throws NotFoundException {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Shape.class));
        List<Point> points = shape.getPoints();
        Double resultArea = 0.0;
        if (Utils.collectionIsNotEmpty(points)) {
            int pointsSize = points.size();
            if (pointsSize == 1) {
                // значит круг
                Double radius = shape.getRadiusInfo().getRadius();
                resultArea = Utils.roundDouble(Math.PI * radius * radius);
            } else {
                //Формула площади Гаусса
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
                resultArea = Utils.roundDouble(Math.abs((a + b - c - d) / 2));
            }
        }
        return new ResponseEntity<>(new CalculatedAreaDTO(resultArea), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> roll(RollDTO rollDTO) throws NotFoundException {
        Long shapeId = rollDTO.getShapeId();
        Double angle = rollDTO.getAngle();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(shapeId, Shape.class));
        List<Point> points = shape.getPoints();
        if (points.size() > 2) {
            Point center = Utils.calculateCenter(points);
            points.sort(new ClockwiseComparator(center));
            double radian = Math.toRadians(angle);
            for (Point point : points) {
                double tempX = center.getX() + ((point.getX() - center.getX()) * Math.cos(radian)) - ((point.getY() - center.getY()) * Math.sin(radian));
                double tempY = center.getY() + ((point.getY() - center.getY()) * Math.cos(radian)) + ((point.getX() - center.getX()) * Math.sin(radian));
                point.setX(tempX);
                point.setY(tempY);
            }
        }
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> move(MoveDTO moveDTO) throws NotFoundException {
        Long shapeId = moveDTO.getShapeId();
        Double x = moveDTO.getX();
        Double y = moveDTO.getY();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(shapeId, Shape.class));
        List<Point> points = shape.getPoints();
        Point center = Utils.calculateCenter(points);
        double dX = x - center.getX();
        double dY = y - center.getY();
        for (Point point : points) {
            point.setX(point.getX() + dX);
            point.setY(point.getY() + dY);
        }
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> scale(ScaleDTO scaleDTO) throws NotFoundException {
        Long shapeId = scaleDTO.getShapeId();
        Double scaleFactor = scaleDTO.getScaleFactor();
        Shape shape = shapesRepository.findById(shapeId).orElseThrow(() -> new NotFoundException(shapeId, Shape.class));
        List<Point> points = shape.getPoints();
        Point center = Utils.calculateCenter(points);
        points.sort(new ClockwiseComparator(center));
        if (points.size() == 1) {
            Double radius = shape.getRadiusInfo().getRadius();
            Double newRadius = Utils.roundDouble(radius * scaleFactor);
            shape.getRadiusInfo().setRadius(newRadius);
        } else {
            for (Point point : points) {
                double tempX = center.getX() + scaleFactor * (point.getX() - center.getX());
                double tempY = center.getY() + scaleFactor * (point.getY() - center.getY());
                point.setX(tempX);
                point.setY(tempY);
            }
        }
        return new ResponseEntity<>(mapper.mapToShapeDTO(shape), HttpStatus.OK);
    }

    private void checkShape(Shape shape) throws NotFoundException, ShapeValidationException {
        Long shapeTypeId = shape.getShapeType().getShapeTypeId();
        ShapeType shapeType = shapeTypeRepository.findById(shapeTypeId)
                .orElseThrow(() -> new NotFoundException(shapeTypeId, ShapeType.class));
        List<Point> shapePoints = shape.getPoints();
        if (Utils.collectionIsNotEmpty(shapePoints)) {
            int shapePointsCount = shapePoints.size();
            if (shapeType.getPointsCount() != shapePointsCount) {
                String message = String.format("Количество точек [%s] переданных в json не соответствует выбранному типу фигуры [%s]",
                        shapePointsCount, shapeType.getName());
                throw new ShapeValidationException(message);
            }
        }
    }
}