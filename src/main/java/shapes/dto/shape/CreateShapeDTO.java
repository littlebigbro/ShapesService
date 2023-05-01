package shapes.dto.shape;

import shapes.domain.Point;
import shapes.domain.RadiusInfo;
import shapes.domain.ShapeType;

import java.util.Date;
import java.util.List;

public class CreateShapeDTO {

    private ShapeType shapeType;
    private RadiusInfo radiusInfo;
    private Date created;
    private Date updated;
    private boolean deleted;
    private List<Point> points;

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

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
