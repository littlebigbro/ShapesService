package exercise1.model.Shapes;

import exercise1.model.IActions.IMovable;
import exercise1.model.IActions.IRollable;
import exercise1.model.IActions.IScalebale;

import java.util.List;
//TODO: Добавить генерацию ID;
public abstract class Shape implements IMovable, IScalebale, IRollable {
    protected String name;
    protected String ruName;
    protected List<Point> points;
    protected List<Double> params;
    protected Point center = new Point();
    protected double radius = 0;
    protected int id;
    private static int IDCounter;

    protected Shape() { }

    protected Shape(List<Double> params) {
        this.id = generateID();
        this.params = params;
    }

    protected Shape(int id, List<Double> params) {
        this.id = id;
        this.params = params;
    }

    public double calculateArea() {
        //Формула площади Гаусса
        double a = 0;
        double b = points.get(points.size() - 1).getX() * points.get(0).getY();
        double c = 0;
        double d = points.get(0).getX() * points.get(points.size() - 1).getY();

        for (int i = 0; i < points.size() - 1; i++) {
            a += (points.get(i).getX() * points.get(i + 1).getY());
        }

        for (int i = 0; i < points.size() - 1; i++) {
            c += (points.get(i + 1).getX() * points.get(i).getY());
        }
        return Math.abs((a + b - c - d) / 2);
    }

    protected int generateID() {
        return ++IDCounter;
    }

    public String getName() {
        return name;
    }

    public String getRuName() {
        return ruName;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
