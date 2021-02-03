package exercise1.Controllers;

import exercise1.IMVC.IController;
import exercise1.IMVC.IModel;
import exercise1.Model.DBLayer.MongoDBRepository;
import exercise1.Model.Shapes.Shape;
import exercise1.Model.Utils.FileAction;

import java.io.File;
import java.io.IOException;
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

    public List<Shape> findById(int i) {
        return model.findById(i);
    }

    public String writeToFile(List<String> figuresInString, File file) throws IOException { //На сколько это правильно???
        return FileAction.writeToNewFile(figuresInString, file);
    }

    public void updatePoints(Shape shape) {
        model.updatePoints(shape);
    }
}
