package exercise1.model.Shapes;

import exercise1.model.Utils.Utils;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Circle extends Shape {

    public Circle() {
    }

    public Circle(List<Double> params) {
        super(params);
        init();
    }

    public Circle(String _id, int id, List<Double> params) {
        super(_id, id, params);
        init();
    }

    private void init() {
        this.name = ShapeTypes.CIRCLE.toString();
        this.ruName = ShapeTypes.CIRCLE.getRuName();
        this.center.setX(params.get(0));
        this.center.setY(params.get(1));
        points = new ArrayList<>();
        points.add(center);
        this.radius = params.get(2);
    }

    @Override
    public double calculateArea() {
        return Utils.roundDouble(Math.PI * radius * radius);
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
    public void roll(double angle) {
    }
}
