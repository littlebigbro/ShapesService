package exercise1;

public interface IShapeStateFactory {
    IMovable setMove();
    IScalebale setScale();
    IRollable setRoll();
}
