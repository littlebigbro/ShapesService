package exercise1.controller;

import exercise1.IMVC.IController;
import exercise1.IMVC.IModel;
import exercise1.model.DBLayer.MongoDBRepository;
import exercise1.model.Shapes.Shape;
import exercise1.model.Utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZKController implements IController {

    @Autowired
    private MongoDBRepository mongoDBRepository;

    public ZKController(IModel model) {
        this.mongoDBRepository = (MongoDBRepository) model;
    }

    @Override
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public boolean enterPassword(@RequestParam String password) {
        return mongoDBRepository.enterPassword(password);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public String getFigureList() {
        return Converter.shapesListToJSON(mongoDBRepository.findAll());
    }

    @RequestMapping(value = "getCount", method = RequestMethod.GET)
    public int getDocCount() {
        return mongoDBRepository.documentCount();
    }

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public String getById(@RequestParam String _id) {
        return Converter.shapesListToJSON(mongoDBRepository.findById(_id));
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public void deleteById(@RequestParam String _id) {
        mongoDBRepository.deleteById(_id);
    }

    @RequestMapping(value = "insertShape", method = RequestMethod.PUT)
    public void insertShape(@RequestParam String json) {
        mongoDBRepository.insert(Converter.jsonToShapes(json).get(0));
    }

    @RequestMapping(value = "updateShape", method = RequestMethod.POST)
    public void updateShape(@RequestParam String json) {
        mongoDBRepository.updateShape(Converter.jsonToShapes(json).get(0));
    }

    @RequestMapping(value = "calcShapeArea", method = RequestMethod.POST)
    public double calculateArea(@RequestParam String json) {
        return mongoDBRepository.calculateArea(json);
    }

    @RequestMapping(value = "resizeShape", method = RequestMethod.POST)
    public String resizeShape(@RequestParam String json, @RequestParam double scale) {
        Shape shape = mongoDBRepository.resizeShape(json, scale);
        return Converter.shapeToJSON(shape);
    }

    @RequestMapping(value = "moveShape", method = RequestMethod.POST)
    public String moveShape(@RequestParam String json, @RequestParam double x, @RequestParam double y) {
        return Converter.shapeToJSON(mongoDBRepository.moveShape(json, x, y));
    }

    @RequestMapping(value = "rollShape", method = RequestMethod.POST)
    public String rollShape(@RequestParam String json, @RequestParam double angle) {
        return Converter.shapeToJSON(mongoDBRepository.rollShape(json, angle));
    }
}
