package exercise1.controller;

import exercise1.IMVC.IController;
import exercise1.IMVC.IModel;
import exercise1.model.DBLayer.MongoDBRepository;
import exercise1.model.Shapes.Shape;
import exercise1.model.Utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class ZKController implements IController {
    //TODO: Перенастроить взаимодействие с фронтом аналогично методу tryLogin
    @Autowired
    private MongoDBRepository mongoDBRepository;

    public ZKController(IModel model) {
        this.mongoDBRepository = (MongoDBRepository) model;
    }

    //TODO: Переписать под логин/пароль
    @Override
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public boolean tryLogin(@RequestBody byte[] bytes) {
        String password = new String(bytes, StandardCharsets.UTF_8);
        return mongoDBRepository.login(password);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout() {
        mongoDBRepository.logout();
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
    public String getById(@RequestBody byte[] bytes) {
        String _id = new String(bytes, StandardCharsets.UTF_8);
        return Converter.shapesListToJSON(mongoDBRepository.findById(_id));
    }

    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public boolean deleteById(@RequestBody byte[] bytes) {
        String _id = new String(bytes, StandardCharsets.UTF_8);
        return mongoDBRepository.deleteById(_id);
    }

    @RequestMapping(value = "insertShape", method = RequestMethod.PUT)
    public boolean insertShape(@RequestBody byte[] bytes) {
        String json = new String(bytes, StandardCharsets.UTF_8);
        return mongoDBRepository.insert(Converter.jsonToShapes(json).get(0));
    }

    @RequestMapping(value = "updateShape", method = RequestMethod.POST)
    public boolean updateShape(@RequestBody byte[] bytes) {
        String json = new String(bytes, StandardCharsets.UTF_8);
        return mongoDBRepository.updateShape(Converter.jsonToShapes(json).get(0));
    }

    @RequestMapping(value = "calcShapeArea", method = RequestMethod.POST)
    public double calculateArea(@RequestBody byte[] bytes) {
        String json = new String(bytes, StandardCharsets.UTF_8);
        return mongoDBRepository.calculateArea(json);
    }
    //TODO: Сделать маппер bytes[] -> map
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
