package exercise1;

import exercise1.circle.Circle;
import exercise1.rectangle.Rectangle;
import exercise1.triangle.Triangle;

import java.util.ArrayList;
import java.util.List;
/*
Задание№1

Создать приложение, которое сможет считывать из файла набор фигур (должно быть не менее 4 различных типов фигур, и при этом хоть один параметр общий).
Желательно использовать абстрактную фабрику.

Доступны должно быть следующие действия:
1) повернуть на
2) переместить на
3) увеличить/ уменьшить в
4) рассчитать площадь

По Вашему усмотрению программа может быть с выводом графическим ( консоль, графическое приложение, веб-проект), либо без (таким образом на вход файл - результат тоже файл)
**/
public class Main {
    public static void main(String[] args) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(1,1));
        IShapeStateFactory circle = new Circle(points, 1);
        IMovable moveCircle = circle.setMove();
        IRollable rollCircle = circle.setRoll();
        IScalebale scaleCircle = circle.setScale();

        System.out.println("Создаем круг...");
        moveCircle.move();
        rollCircle.roll();
        scaleCircle.changesize();


        IShapeStateFactory triangle = new Triangle(points);
        IMovable moveTriangle = triangle.setMove();
        IRollable rollTriangle = triangle.setRoll();
        IScalebale scaleTriangle = triangle.setScale();

        System.out.println("Создаем треугольник...");
        moveTriangle.move();
        rollTriangle.roll();
        scaleTriangle.changesize();

        IShapeStateFactory rectangle = new Rectangle(points);
        IMovable moveTRectangle = rectangle.setMove();
        IRollable rollRectangle= rectangle.setRoll();
        IScalebale scaleRectangle = rectangle.setScale();

        System.out.println("Создаем прямоугольник...");
        moveTRectangle.move();
        rollRectangle.roll();
        scaleRectangle.changesize();


    }
}
