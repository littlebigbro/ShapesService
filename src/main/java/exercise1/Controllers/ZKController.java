package exercise1.Controllers;

import exercise1.IMVC.IController;
import exercise1.IMVC.IModel;
import exercise1.Model.DBLayer.MongoDBRepository;
import exercise1.Model.Shapes.Shape;
import exercise1.Model.Utils.Converter;
import exercise1.Model.Utils.FileAction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ZKController implements IController {
    private MongoDBRepository model;

    public ZKController(IModel model) {
        this.model = (MongoDBRepository) model;
    }

    @Override
    public boolean setPassword(String password) {
        model.setPassword(password);
        return model.isPasswordIsCorrect();
    }

    public String getFigureList() {
        return Converter.shapesToJSON(model.findAll());
    }

    public int getDocCount() {
        return model.documentCount();
    }

    public void deleteById(int shapeId) {
        model.deleteById(shapeId);
    }

    public void insertShape(String json) {
        model.insert(Converter.JSONtoShapes(json).get(0));
    }

    public String findById(int i) {
        return Converter.shapesToJSON(model.findById(i));
    }

    public String writeToFile(List<String> figuresInString, File file) throws IOException {
        return FileAction.writeToNewFile(figuresInString, file);
    }

    public void updatePoints(String json) {
        model.updatePoints(Converter.JSONtoShapes(json).get(0));
    }
}
