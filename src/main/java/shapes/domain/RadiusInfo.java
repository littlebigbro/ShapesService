package shapes.domain;

import javax.persistence.*;

@Entity
@Table(name = "radius_info")
public class RadiusInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="radius_id")
    private int radiusId;

    @OneToOne()
    @JoinColumn(name = "shape_id", referencedColumnName = "shape_id")
    private Shape shape;

    @Column(name = "radius")
    private double radius;

    public RadiusInfo() {
    }

    public int getRadiusId() {
        return radiusId;
    }

    public void setRadiusId(int radiusId) {
        this.radiusId = radiusId;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
