package exercise1.Menu;

import exercise1.FigureHandler;
import exercise1.Shapes.*;
import exercise1.Utils.FileAction;
import exercise1.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static FigureHandler fh;
    private static Scanner in;
    private static boolean isShown;
    public static void start() {
        fh = new FigureHandler();
        fh.handle("/test.txt");
        if (fh != null && !fh.getFigureList().isEmpty()) {
            System.out.println("Файл успешно прочитан");
            isShown = true;
            menu(isShown);
        } else {
            System.out.println("Файл не прочитан, либо пуст");
        }
    }

    private static void menu(boolean isShown) {
        in = new Scanner(System.in);
        while (isShown) {
            System.out.println("1 - Вывести список всех фигур\n" +
                    "2 - Выбрать фигуру по номеру\n" +
                    "3 - Добавить фигуру\n" +
                    "4 - Удалить фигуру\n" +
                    "5 - Сохранить изменения\n" +
                    "6 - Выход");
            int mainMenuNum = in.nextInt();
            switch (mainMenuNum) {
                case 1: {
                    int numRow = 0;
                    for (Shape figure : fh.getFigureList()) {
                        numRow++;
                        System.out.println(numRow + ") " + figure.getRuName() + " " + Utils.getPointsAsString(figure.getPoints()));
                    }
                    break;
                }
                case 2: {
                    chooseFigure();
                    break;
                }
                case 3: {
                    fh.getFigureList().add(addNewFigure());
                    break;
                }
                case 4: {
                    choiceToRemove();
                    break;
                }
                case 5: {
                    saveToFile();
                    break;
                }
                case 6: {
                    System.out.println("Все несохраненные изменения будут потеряны. Завершить работу программы? (Д/Н)");
                    String choice = in.next();
                    choiceToExit(choice);
                    break;
                }
                default: {
                    System.out.println("Вы указали неверный пункт меню. Повторите ввод!");
                }
            }
            System.out.flush();
        }
    }

    private static void chooseFigure() {
        String fig;
        if (fh.getFigureList().size() <= 1) {
            fig = " фигура";
        } else if (fh.getFigureList().size() <= 4) {
            fig = " фигуры";
        } else {
            fig = " фигур";
        }
        System.out.println("Доступно " + fh.getFigureList().size() + fig + ". Выберите одну: ");
        int figureNum = in.nextInt();
        if (figureNum <= fh.getFigureList().size() && figureNum >= 1) {
            FigureMenu.handle(fh.getFigureList().get(figureNum - 1));
            isShown = false;
        } else {
            System.out.println("Некорректный ввод");
            chooseFigure();
        }
    }

    private static Shape addNewFigure() {
        System.out.println("Выберите тип фигуры для добавления\n" +
                "1 - Треугольник\n" +
                "2 - Круг\n" +
                "3 - Прямоугольник");
        Shape shape = null;
        int figureType = in.nextInt();
        switch (figureType) {
            case 1: {
                shape = new Triangle(addParams(3));
                break;
            }
            case 2: {
                List<Double> params = addParams(1);
                System.out.print("Укажите радиус круга - ");
                params.add(in.nextDouble());
                shape = new Circle(params);
                break;
            }
            case 3: {
                shape = new Rectangle(addParams(4));
                break;
            }
            default: {
                System.out.println("Некорректный ввод.");
                addNewFigure();
            }
        }
        return shape;
    }

    private static List<Double> addParams(int pointsCount) {
        List<Double> params = new ArrayList<>();
        int count = 0;
        double x, y;
        System.out.print("Введите координаты точек(Например X = 0,4)");
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

    private static void choiceToRemove() {
        System.out.println("Укажите номер удаляемой фигуры");
        int figureNum = in.nextInt();
        if (figureNum <= fh.getFigureList().size() && figureNum >= 1) {
            fh.getFigureList().remove(figureNum - 1);
        } else {
            System.out.println("Некорректный ввод");
            choiceToRemove();
        }
    }

    private static void saveToFile() {
        List<String> figuresInString = new ArrayList<>();
        for (Shape figure : fh.getFigureList()) {
            figuresInString.add(figure.getName() + " " + Utils.getPointsAsString(figure.getPoints()));

        }
        try {
            String path = "H:/Java/javalearn/src/main/resources/out.txt";
            FileAction.writeToNewFile(figuresInString, new File(path));
            System.out.println("Сохранение завершено");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void choiceToExit(String choice) {
        if ("д".equalsIgnoreCase(choice)) {
            System.exit(0);
        } else if ("н".equalsIgnoreCase(choice)) {
            System.out.flush();
        } else {
            System.out.println("Некорректный ввод");
            choiceToExit(choice);
        }
    }


    static class FigureMenu {
        private static boolean figureMenuIsShown = true;

        public static void handle(Shape figure) {
            if (figure.getRuName().equalsIgnoreCase(ShapeTypes.CIRCLE.getRuName())) {
                System.out.println("Выбранная фигура: " + ShapeTypes.CIRCLE.getRuName() +
                        " - с центром в координатах " + Utils.getPointsAsString(figure.getPoints()) +
                        ", и радиусом = " + figure.getRadius());
            } else {
                System.out.println("Выбранная фигура: " + figure.getRuName() +
                        " - с координатам в точках - " + Utils.getPointsAsString(figure.getPoints()));
            }

            System.out.println("Выберите действие:" +
                    "\n1 - Подвинуть" +
                    "\n2 - Повернуть" +
                    "\n3 - Смасштабировать" +
                    "\n4 - Расчитать площадь" +
                    "\n5 - Выйти в предыдущее меню");
            Scanner in = new Scanner(System.in);
            int figureMenuIndex = in.nextInt();
            switch (figureMenuIndex) {
                case 1: {
                    System.out.println("Введите координаты нового центра(Например X = 0,4).\nX = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();
                    figure.move(x, y);
                    break;
                }
                case 2: {
                    System.out.println("Введите угол в градусах для поворота(Например 30,0).\nУгол = ");
                    double angle = in.nextDouble();
                    figure.roll(angle);
                    break;
                }
                case 3: {
                    System.out.println("Введите коэффициент масштабирования.\n" +
                            "Для уменьшения коэффициент должен быть в пределах [0;1,0).\n" +
                            "Для увеличения коэффициент должен быть больше 1,0.\n" +
                            "Коэффициент = ");
                    double angle = in.nextDouble();
                    figure.changeSize(angle);
                    break;
                }
                case 4: {
                    System.out.println(figure.calculateArea());
                    break;
                }
                case 5: {
                    figureMenuIsShown = false;
                    break;
                }
                default: {
                    System.out.println("Введите корректный номер меню");
                }
            }
            if (figureMenuIsShown) {
                handle(figure);
            } else {
                isShown = true;
            }
        }
    }
}
