package exercise1.Model.Shapes;

public enum ShapeTypes {

    TRIANGLE ("Треугольник"),
    RECTANGLE("Прямоугольник"),
    CIRCLE("Круг");

    private String ruName;

    ShapeTypes(String name) {
        this.ruName = name;
    }

    public String getRuName() {
        return ruName;
    }
}
