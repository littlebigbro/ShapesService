package shapes.old.Shapes;

import shapes.old.utils.Utils;

public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = Utils.roundDouble(x);
        this.y = Utils.roundDouble(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = Utils.roundDouble(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = Utils.roundDouble(y);
    }
}
