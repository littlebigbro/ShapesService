package shapes.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shapetype")
public class ShapeType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="shapetype_id")
    private int shapeTypeId;

    @OneToOne(mappedBy = "shapeType")
    private Shape shape;

    @Column(name = "systemname")
    private String systemName;

    @Column(name = "name")
    private String name;

    public ShapeType() {
    }

    public ShapeType(String systemName, String name) {
        this.systemName = systemName;
        this.name = name;
    }

    public int getShapeTypeId() {
        return shapeTypeId;
    }

    public void setShapeTypeId(int shapeTypeId) {
        this.shapeTypeId = shapeTypeId;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
