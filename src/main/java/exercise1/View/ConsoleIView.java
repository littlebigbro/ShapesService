package exercise1.View;

import exercise1.Controllers.DBController;
import exercise1.IMVC.IController;
import exercise1.IMVC.IView;
import exercise1.model.Shapes.*;
import exercise1.model.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleIView implements IView {
    private Scanner in;
    private boolean isShown;
    private DBController controller;
    public ConsoleIView(IController controller) {
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
        if (controller.setPassword(password)){
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
                    "5 - Сохранить БД в файл\n" +
                    "6 - Выход\n");
            int mainMenuNum = in.nextInt();
            switch (mainMenuNum) {
                case 1: {
                    int numRow = 0;
                    for (Shape figure : controller.getFigureList()) {
                        numRow++;
                        System.out.println(numRow + ") " + "Id = " + figure.getId() + ", " + figure.getRuName() + " " + Utils.getPointsAsString(figure.getPoints()));
                    }
                    System.out.println("");
                    break;
                }
//                case 2: {
//                    chooseFigure();
//                    break;
//                }
                case 3: {
                    addNewFigure();
                    break;
                }
                case 4: {
                    deleteShapeById();
                    break;
                }
//                case 5: {
//                    saveToFile();
//                    break;
//                }
                case 6: {
                    System.out.println("Все несохраненные изменения будут потеряны. Завершить работу программы? (Д/Н)");
                    String choice = in.next();
                    exitApp(choice);
                    break;
                }
                default: {
                    System.out.println("Вы указали неверный пункт меню. Повторите ввод!");
                }
            }
            System.out.flush();
        }
    }

    private void addNewFigure() {
        System.out.println("Выберите тип фигуры для добавления\n" +
                "1 - Треугольник\n" +
                "2 - Круг\n" +
                "3 - Прямоугольник");
        int figureType = in.nextInt();
        List<Double> params = new ArrayList<>();
        String shapeType = "";
        switch (figureType) {
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
                params.add(in.nextDouble());;
                break;
            }
            case 3: {
                shapeType = ShapeTypes.RECTANGLE.toString();
                System.out.print("Введите координаты точек(Например X = 0,4)");
                params = addParams(4);
                break;
            }
            default: {
                System.out.println("Некорректный ввод.");
                addNewFigure();
            }
        }
        controller.insertShape(shapeType, params);
    }

    private List<Double> addParams ( int pointsCount){
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

    private void exitApp(String choice) {
        if ("д".equalsIgnoreCase(choice)) {
            System.exit(0);
        } else if ("н".equalsIgnoreCase(choice)) {
            System.out.flush();
        } else {
            System.out.println("Некорректный ввод");
            exitApp(choice);
        }
    }
}
