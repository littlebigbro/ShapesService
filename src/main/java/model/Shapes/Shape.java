package model.Shapes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.IActions.IMovable;
import model.IActions.IRollable;
import model.IActions.IScalebale;
import model.Utils.Utils;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Triangle.class, name = "TRIANGLE"),
        @JsonSubTypes.Type(value = Circle.class, name = "CIRCLE"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "RECTANGLE"),
})

@JsonAutoDetect
public abstract class Shape implements IMovable, IScalebale, IRollable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("shapeType")
    protected String name;
    protected String ruName;
    protected List<Point> points;
    protected List<Double> params;
    protected Point center = new Point();
    protected double radius = 0;
    protected String _id;
    protected int id;
    private static int IDCounter;

    protected Shape() {
    }

    protected Shape(List<Double> params) {
        this.id = generateID();
        this.params = params;
    }

    protected Shape(String _id, int id, List<Double> params) {
        this._id = _id;
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
        return Utils.roundDouble(Math.abs((a + b - c - d) / 2));
    }

    protected int generateID() {
        return ++IDCounter;
    }

    protected int generateID(int _id) {
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

    public String get_id() {
        return _id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRuName()).append(" c id = ").append(getId()).append(", и точками: ");
        for (int i = 0; i < points.size(); i++) {
            sb.append("x = ").append(points.get(i).getX()).append(", y = ").append(points.get(i).getY());
            if (i + 1 != points.size()) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
