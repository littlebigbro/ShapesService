package exercise1.View;

import exercise1.Controllers.DBController;
import exercise1.IMVC.IController;
import exercise1.IMVC.IView;
import exercise1.Model.Shapes.Shape;
import exercise1.Model.Shapes.ShapeTypes;
import exercise1.Model.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView implements IView {
    private Scanner in;
    private boolean isShown;
    private DBController controller;
    private ConsoleSubMenu subMenu;

    public ConsoleView(IController controller) {
        this.controller = (DBController) controller;
    }

    @Override
    public void init() {
        System.out.print("Здравствуйте!\n" +
                "Для работы программы, пожалуйста, введите пароль для подключения к базе данных: ");
        enterThePassword();
        menu(isShown);
    }

    private void enterThePassword() {
        in = new Scanner(System.in);
        String password = in.nextLine();
        if (controller.setPassword(password)) {
            System.out.println("Подключение к базе данных успешно!");
            isShown = true;
        } else {
            System.out.println("Неверный пароль! Повторите ввод.");
            enterThePassword();
        }
    }

    private void menu(boolean isShown) {
        in = new Scanner(System.in);
        while (isShown) {
            System.out.print("1 - Вывести список всех фигур\n" +
                    "2 - Выбрать фигуру по номеру\n" +
                    "3 - Добавить фигуру\n" +
                    "4 - Удалить фигуру\n" +
                    "5 - Сохранить фигуры из БД в файл\n" +
                    "6 - Выход\n");
            String userChoice = in.nextLine();
            try {
                int menuNumber = Integer.parseInt(userChoice);
                switch (menuNumber) {
                    case 1: {
                        int numRow = 0;
                        for (Shape figure : controller.getFigureList()) {
                            numRow++;
                            System.out.println(numRow + ") " + "Id = " + figure.getId() + ", " + figure.getRuName() + " " + Utils.getPointsAsString(figure.getPoints()));
                        }
                        System.out.println("");
                        break;
                    }
                    case 2: {
                        chooseFigure();
                        break;
                    }
                    case 3: {
                        addNewFigure();
                        break;
                    }
                    case 4: {
                        deleteShapeById();
                        break;
                    }
                    case 5: {
                        saveToFile();
                        break;
                    }
                    case 6: {
                        exitApp();
                        break;
                    }
                    default: {
                        System.out.println("Вы указали неверный пункт меню. Повторите ввод!");
                    }
                }
                System.out.flush();
            } catch (Exception e) {
                System.out.println("Вы указали неверный пункт меню. Повторите ввод!");
            }
        }
    }

    private void chooseFigure() {
        String fig;
        int figureCount = controller.getFigureList().size();
        if (figureCount <= 1) {
            fig = " фигура";
        } else if (figureCount <= 4) {
            fig = " фигуры";
        } else {
            fig = " фигур";
        }
        System.out.println("Доступно " + figureCount + fig + ". Выберите одну: ");
        String figureNumStr = in.nextLine();
        try {
            int figureNum = Integer.parseInt(figureNumStr);
            if (figureNum <= figureCount && figureNum >= 1) {
                List<Shape> shapes = controller.findById(figureNum);
                isShown = false;
                subMenu = new ConsoleSubMenu(controller, shapes.get(0));
                subMenu.init();
//                while (subMenu.isActive()) {
//
//                }
                isShown = true;
            } else {
                System.out.println("Некорректный ввод");
                chooseFigure();
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод");
            chooseFigure();
        }
    }

    private void addNewFigure() {
        System.out.println("Выберите тип фигуры для добавления\n" +
                "1 - Треугольник\n" +
                "2 - Круг\n" +
                "3 - Прямоугольник");
        String figureType = in.nextLine();
        try {
            int figureNum = Integer.parseInt(figureType);
            List<Double> params = new ArrayList<>();
            String shapeType = "";
            switch (figureNum) {
                case 1: {
                    shapeType = ShapeTypes.TRIANGLE.toString();
                    System.out.print("Введите координаты точек(Например X = 0,4)");
                    addParams(3);
                    break;
                }
                case 2: {
                    shapeType = ShapeTypes.CIRCLE.toString();
                    System.out.print("Введите координаты центра(Например X = 0,4)");
                    params = addParams(1);
                    System.out.print("Укажите радиус круга - ");
                    params.add(in.nextDouble());
                    ;
                    break;
                }
                case 3: {
                    shapeType = ShapeTypes.RECTANGLE.toString();
                    System.out.print("Введите координаты точек(Например X = 0,4)");
                    params = addParams(4);
                    break;
                }
            }
            if (shapeType != null && !shapeType.isEmpty()) {
                controller.insertShape(shapeType, params);
            }
        } catch (Exception e) {
            System.out.println("Некорректный ввод.");
            addNewFigure();
        }
    }

    private List<Double> addParams(int pointsCount) {
        List<Double> params = new ArrayList<>();
        int count = 0;
        double x, y;
        while (pointsCount > count) {
            count++;
            System.out.print("\nX = ");
            x = in.nextDouble();
            params.add(x);
            System.out.print("Y = ");
            y = in.nextDouble();
            params.add(y);
        }
        return params;
    }

    private void deleteShapeById() {
        System.out.println("Укажите Id удаляемой фигуры");
        int figureNum = in.nextInt();
        if (figureNum <= controller.getDocCount() && figureNum >= 1) {
            controller.deleteById(figureNum);
        } else {
            System.out.println("Некорректный ввод");
            deleteShapeById();
        }
    }

    private void saveToFile() throws IOException {
        List<String> figuresInString = new ArrayList<>();
        for (Shape figure : controller.getFigureList()) {
            figuresInString.add(figure.getName() + " " + Utils.getPointsAsString(figure.getPoints()));
        }
        String path = "C:/Java/out.txt";
        if ("SUCCESS".equals(controller.writeToFile(figuresInString, new File(path)))) {
            System.out.println("Сохранение успешно завершено!");
        } else {
            System.out.println("Сохранение завершилось с ошибкой!");
        }
    }

    private void exitApp() {
        System.out.println("Все несохраненные изменения будут потеряны. Завершить работу программы? (Д/Н)");
        String choice = in.next();
        if ("д".equalsIgnoreCase(choice)) {
            System.exit(0);
        } else if ("н".equalsIgnoreCase(choice)) {
            System.out.flush();
        } else {
            System.out.println("Некорректный ввод");
            exitApp();
        }
    }
}
