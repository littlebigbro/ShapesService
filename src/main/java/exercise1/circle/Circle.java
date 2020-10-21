package exercise1.circle;

import exercise1.*;
import java.util.List;

public class Circle extends Shape implements IShapeStateFactory {
    //Создать конструктор принимающий параметры для круга
    public Circle(List<Point> points, double radius) {
        this.center = points.get(0);
        this.radius = radius;
    }
    @Override
    public IMovable setMove() {
        return new Move();
    }

    @Override
    public IScalebale setScale() {
        return new Scale();
    }

    @Override
    public IRollable setRoll() {
        return new Roll();
    }

    @Override
    protected double calculateArea() {
        return Math.PI * radius * radius;
    }
}
