package exercise1.Shapes;

import java.util.List;

public class Circle extends Shape {

    public Circle(List<Double> params) {
        this.center.setX(params.get(0));
        this.center.setY(params.get(1));
        this.radius = params.get(2);
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void move(double x, double y) {
        this.center.setX(x);
        this.center.setY(y);
    }

    @Override
    public void changeSize(double scaleFactor) {
        this.radius *= scaleFactor;
    }

    @Override
    public void roll(double angle) { }
}
