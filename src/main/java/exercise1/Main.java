package exercise1;

import exercise1.circle.Circle;

import java.util.ArrayList;
import java.util.List;

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
    }
}
