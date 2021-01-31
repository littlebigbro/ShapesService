package exercise1.Controllers;

import exercise1.model.DBLayer.MongoDBRepository;
import exercise1.IMVC.IController;
import exercise1.IMVC.IModel;
import exercise1.model.Shapes.Shape;

import java.util.List;

public class DBController implements IController {
    private MongoDBRepository model;

    public DBController(IModel model) {
        this.model = (MongoDBRepository) model;
    }

    @Override
    public boolean setPassword(String password) {
        model.setPassword(password);
        return model.isPasswordIsCorrect();
    }

    public List<Shape> getFigureList() {
        return model.findAll();
    }

    public int getDocCount() {
        return model.documentCount();
    }

    public void deleteById(int figureNum) {
        model.deleteById(figureNum);
    }

    public void insertShape(String shapeName, List<Double> params) {
        model.insert(model.createShape(shapeName, params));
    }
}
