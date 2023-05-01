package shapes.old;

public class ZKController {
/*


    @Override
    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public boolean tryLogin(@RequestBody byte[] bytes) {
        return mongoDBRepository.login(Converter.bytesToMap(bytes));
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

    @RequestMapping(value = "getById", method = RequestMethod.POST)
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

    @RequestMapping(value = "getNewId", method = RequestMethod.GET)
    public int getNewId() {
        return mongoDBRepository.generateID();
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

    @RequestMapping(value = "scaleShape", method = RequestMethod.POST)
    public String scaleShape(@RequestBody byte[] bytes) {
        return Converter.shapeToJSON(mongoDBRepository.scaleShape(Converter.bytesToMap(bytes)));
    }

    @RequestMapping(value = "moveShape", method = RequestMethod.POST)
    public String moveShape(@RequestBody byte[] bytes) {
        return Converter.shapeToJSON(mongoDBRepository.moveShape(Converter.bytesToMap(bytes)));
    }

    @RequestMapping(value = "rollShape", method = RequestMethod.POST)
    public String rollShape(@RequestBody byte[] bytes) {
        return Converter.shapeToJSON(mongoDBRepository.rollShape(Converter.bytesToMap(bytes)));
    }*/
}
