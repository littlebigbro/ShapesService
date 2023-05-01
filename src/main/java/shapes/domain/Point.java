package shapes.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "point")
public class Point implements Serializable {
    @Id
    @Column(name = "point_id")
    @SequenceGenerator(name = "pointIdSeq", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointIdSeq")
    private int pointId;

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

    @Override
    public String toString() {
        return String.format("Точка с id = %s имеет координаты: x = %s, y = %s}", pointId, x, y);
    }
}
