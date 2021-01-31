package exercise1.Controllers;

public class FileController {
//    private static FigureHandler fh;
//
//    public FileController(List<Shape> figureList) {
//        this.figureList = figureList;6
//    }
//
//    public static void chooseFigure() {
//
//        System.out.println("Доступно " + fh.getFigureList().size() + fig + ". Выберите одну: ");
//        int figureNum = in.nextInt();
//        if (figureNum <= fh.getFigureList().size() && figureNum >= 1) {
//            Menu.FigureMenu.handle(fh.getFigureList().get(figureNum - 1));
//            isShown = false;
//        } else {
//            System.out.println("Некорректный ввод");
//            chooseFigure();
//        }
//    }
//
//    public static Shape addNewFigure() {
//        System.out.println("Выберите тип фигуры для добавления\n" +
//                "1 - Треугольник\n" +
//                "2 - Круг\n" +
//                "3 - Прямоугольник");
//        Shape shape = null;
//        int figureType = in.nextInt();
//        switch (figureType) {
//            case 1: {
//                shape = new Triangle(addParams(3));
//                break;
//            }
//            case 2: {
//                List<Double> params = addParams(1);
//                System.out.print("Укажите радиус круга - ");
//                params.add(in.nextDouble());
//                shape = new Circle(params);
//                break;
//            }
//            case 3: {
//                shape = new Rectangle(addParams(4));
//                break;
//            }
//            default: {
//                System.out.println("Некорректный ввод.");
//                addNewFigure();
//            }
//        }
//        return shape;
//    }
//
//    public static List<Double> addParams(int pointsCount) {
//        List<Double> params = new ArrayList<>();
//        int count = 0;
//        double x, y;
//        System.out.print("Введите координаты точек(Например X = 0,4)");
//        while (pointsCount > count) {
//            count++;
//            System.out.print("\nX = ");
//            x = in.nextDouble();
//            params.add(x);
//            System.out.print("Y = ");
//            y = in.nextDouble();
//            params.add(y);
//        }
//        return params;
//    }
//
//    public static void choiceToRemove() {
//        System.out.println("Укажите номер удаляемой фигуры");
//        int figureNum = in.nextInt();
//        if (figureNum <= fh.getFigureList().size() && figureNum >= 1) {
//            fh.getFigureList().remove(figureNum - 1);
//        } else {
//            System.out.println("Некорректный ввод");
//            choiceToRemove();
//        }
//    }
//
//    public static void saveToFile() {
//        List<String> figuresInString = new ArrayList<>();
//        for (Shape figure : fh.getFigureList()) {
//            figuresInString.add(figure.getName() + " " + Utils.getPointsAsString(figure.getPoints()));
//
//        }
//        try {
//            String path = "H:/Java/javalearn/src/main/resources/out.txt";
//            FileAction.writeToNewFile(figuresInString, new File(path));
//            System.out.println("Сохранение завершено");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void choiceToExit(String choice) {
//        if ("д".equalsIgnoreCase(choice)) {
//            System.exit(0);
//        } else if ("н".equalsIgnoreCase(choice)) {
//            System.out.flush();
//        } else {
//            System.out.println("Некорректный ввод");
//            choiceToExit(choice);
//        }
//    }
//
//    private List<Shape> figureList = new ArrayList<>();
//
//    public void handle(String filePath) {
//        InputStream is = getClass().getResourceAsStream(filePath);
//        List<String> dataFromFile = FileAction.readFileToList(is);
//        for (String line : dataFromFile) {
//            String[] lineAsMassive= line.split("[ ]");
//            IShapeFactory shapeFactory = createShapeFactory(parseName(lineAsMassive));
//            figureList.add(shapeFactory.createFigure(parsePointValues(lineAsMassive)));
//        }
//    }
//
//    private String parseName(String[] line) {
//        return line[0];
//    }
//
//    private List<Double> parsePointValues(String[] line) {
//        List <Double> pointValues = new ArrayList<>();
//        for (int i = 1; i < line.length; i++) { // 0 элемент это наименование фигуры.
//            pointValues.add(Double.parseDouble(line[i]));
//        }
//        return pointValues;
//    }
//
//    public static IShapeFactory createShapeFactory(String factoryName) {
//        if (ShapeTypes.CIRCLE.toString().equalsIgnoreCase(factoryName)) {
//            return new CircleFactory();
//        } else if (ShapeTypes.TRIANGLE.toString().equalsIgnoreCase(factoryName)) {
//            return new TriangleFactory();
//        } else if (ShapeTypes.RECTANGLE.toString().equalsIgnoreCase(factoryName)) {
//            return new RectangleFactory();
//        } else {
//            throw new RuntimeException(factoryName + " неизвестная фигура");
//        }
//    }
//
//    public List<Shape> getFigureList() {
//        return figureList;
//    }
//
//    public void setFigureList(List<Shape> figureList) {
//        this.figureList = figureList;
//    }
}
