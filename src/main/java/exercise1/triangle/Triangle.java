package exercise1.triangle;

import exercise1.*;

public class Triangle extends Shape implements IShapeStateFactory {
    //Создать конструктор принимающий параметры для треугольника
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
