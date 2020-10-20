package exercise1.rectangle;

import exercise1.*;

public class Rectangle extends Shape implements IShapeStateFactory {
    //Создать конструктор принимающий параметры для прямоугольника
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
}
