package shapes.dto.shapetype;

public class ShapeTypeDTO {
    private int shapeTypeId;
    private String systemName;
    private String name;

    public int getShapeTypeId() {
        return shapeTypeId;
    }

    public void setShapeTypeId(int shapeTypeId) {
        this.shapeTypeId = shapeTypeId;
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
