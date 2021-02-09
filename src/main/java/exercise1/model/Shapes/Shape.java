package exercise1.model.Shapes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import exercise1.model.IActions.IMovable;
import exercise1.model.IActions.IRollable;
import exercise1.model.IActions.IScalebale;
import exercise1.model.Utils.Utils;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Triangle.class, name = "Triangle"),
        @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
        @JsonSubTypes.Type(value = Rectangle.class, name = "Rectangle"),
})

@JsonAutoDetect
public abstract class Shape implements IMovable, IScalebale, IRollable {
    @JsonProperty("shapeType")
    protected String name;
    protected String ruName;
    protected List<Point> points;
    protected List<Double> params;
    protected Point center = new Point();
    protected double radius = 0;
    protected int id;
    private static int IDCounter;

    //TODO: добавить запись _id из Монго в поле dbId, поправить генерацию поля id
    protected Shape() {
    }

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
        return Utils.roundDouble(Math.abs((a + b - c - d) / 2));
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
