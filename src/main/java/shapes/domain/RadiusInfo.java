package shapes.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "radius_info")
public class RadiusInfo implements Serializable {
    @Id
    @Column(name = "radius_id")
    @SequenceGenerator(name = "radiusIdSeq", sequenceName = "radius_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "radiusIdSeq")
    private int radiusId;

    @Column(name = "radius")
    private double radius;

    public RadiusInfo() {
    }

    public RadiusInfo(double radius) {
        this.radius = radius;
    }

    public int getRadiusId() {
        return radiusId;
    }

    public void setRadiusId(int radiusId) {
        this.radiusId = radiusId;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
