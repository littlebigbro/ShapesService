package shapes.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shape")
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shape_id")
    private int shapeId;

    @OneToOne()
    @JoinColumn(name = "shapetype_id", referencedColumnName = "shapetype_id")
    private ShapeType shapeType;

    @OneToOne(mappedBy = "shape")
    private RadiusInfo radiusInfo;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "parentShape")
    private List<Point> points;

    public Shape() {
    }

    public int getShapeId() {
        return shapeId;
    }

    public void setShapeId(int shapeId) {
        this.shapeId = shapeId;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public RadiusInfo getRadiusInfo() {
        return radiusInfo;
    }

    public void setRadiusInfo(RadiusInfo radiusInfo) {
        this.radiusInfo = radiusInfo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
