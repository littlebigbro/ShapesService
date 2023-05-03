package shapes.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shapes.exceptions.NotFoundException;
import shapes.mappers.ShapesMapper;
import shapes.models.Point;
import shapes.models.Shape;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.repositories.ShapesRepository;
import shapes.services.ShapesService;
import shapes.utils.ClockwiseComparator;
import shapes.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ShapesServiceImpl implements ShapesService {
    private final ShapesRepository shapesRepository;

    @Override
    public ResponseEntity<List<ShapeDTO>> getAll() {
        List<Shape> shapes = shapesRepository.findAll();
        return new ResponseEntity<>(
                shapes.stream()
                        .map(ShapesMapper.MAPPER::mapToShapeDTO)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShapeDTO> getById(long id) throws NotFoundException {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Shape.class));
        ShapeDTO shapeDTO = ShapesMapper.MAPPER.mapToShapeDTO(shape);
        return new ResponseEntity<>(shapeDTO, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> createShape(CreateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<HttpStatus> updateShape(UpdateShapeDTO shapeDTO) {
        Shape shape = ShapesMapper.MAPPER.mapToShape(shapeDTO);
        shapesRepository.save(shape);
        return ResponseEntity.ok(HttpStatus.OK);
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
    public ResponseEntity<Map<String, Double>> calculateArea(long id) throws NotFoundException {
        Shape shape = shapesRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Shape.class));
        List<Point> points = shape.getPoints();
        Double resultArea = 0.0;
        if (Utils.isNotEmpty(points)) {
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
        Map<String, Double> result = new HashMap<>();
        result.put("calculatedArea", resultArea);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        return new ResponseEntity<>(ShapesMapper.MAPPER.mapToShapeDTO(shape), HttpStatus.OK);
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
        return new ResponseEntity<>(ShapesMapper.MAPPER.mapToShapeDTO(shape), HttpStatus.OK);
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
        return new ResponseEntity<>(ShapesMapper.MAPPER.mapToShapeDTO(shape), HttpStatus.OK);
    }
}