package shapes.domain;

import javax.persistence.*;

@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "point_id")
    private int pointId;

    @ManyToOne
    @JoinColumn(name = "shape_id", referencedColumnName = "shape_id")
    private Shape parentShape;

    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Shape getParentShape() {
        return parentShape;
    }

    public void setParentShape(Shape parentShape) {
        this.parentShape = parentShape;
    }

    @Override
    public String toString() {
        return String.format("Точка с id = %s имеет координаты: x = %s, y = %s}", pointId, x, y);
    }
}
