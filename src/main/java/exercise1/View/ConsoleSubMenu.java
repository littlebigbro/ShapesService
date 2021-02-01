package exercise1.View;

import exercise1.Controllers.DBController;
import exercise1.IMVC.IController;
import exercise1.IMVC.IView;
import exercise1.Model.Shapes.Shape;
import exercise1.Model.Shapes.ShapeTypes;
import exercise1.Model.Utils.Utils;

import java.util.Scanner;


public class ConsoleSubMenu implements IView {
    private DBController controller;
    private Shape shape;
    private static boolean figureMenuIsShown = true;

    public ConsoleSubMenu(IController controller, Shape shape) {
        this.controller = (DBController) controller;
        this.shape = shape;
    }

    public void init() {
        if (shape.getRuName().equalsIgnoreCase(ShapeTypes.CIRCLE.getRuName())) {
            System.out.println("Выбранная фигура: " + ShapeTypes.CIRCLE.getRuName() +
                    " - с центром в координатах " + Utils.getPointsAsString(shape.getPoints()) +
                    ", и радиусом = " + shape.getRadius());
        } else {
            System.out.println("Выбранная фигура: " + shape.getRuName() +
                    " - с координатами в точках - " + Utils.getPointsAsString(shape.getPoints()));
        }

        System.out.println("Выберите действие:" +
                "\n1 - Подвинуть" +
                "\n2 - Повернуть" +
                "\n3 - Смасштабировать" +
                "\n4 - Расчитать площадь" +
                "\n5 - Выйти в предыдущее меню");
        Scanner in = new Scanner(System.in);
        String userChoice = in.nextLine();
        try {
            int figureMenuIndex = Integer.parseInt(userChoice);
            switch (figureMenuIndex) {
                case 1: {
                    System.out.print("Введите координаты нового центра(Например X = 0,4).\nX = ");
                    double x = in.nextDouble();
                    System.out.print("Y = ");
                    double y = in.nextDouble();
                    shape.move(x, y);
                    controller.updatePoints(shape);
                    break;
                }
                case 2: {
                    System.out.print("Введите угол в градусах для поворота(Например 30,0).\nУгол = ");
                    double angle = in.nextDouble();
                    shape.roll(angle);
                    controller.updatePoints(shape);
                    break;
                }
                case 3: {
                    System.out.print("Введите коэффициент масштабирования.\n" +
                            "Для уменьшения коэффициент должен быть в пределах [0;1,0).\n" +
                            "Для увеличения коэффициент должен быть больше 1,0.\n" +
                            "Коэффициент = ");
                    double angle = in.nextDouble();
                    shape.changeSize(angle);
                    controller.updatePoints(shape);
                    break;
                }
                case 4: {
                    System.out.println(shape.calculateArea());
                    break;
                }
                case 5: {
                    figureMenuIsShown = false;
                    break;
                }
                default: {
                    System.out.println("Неверный ввод");
                }
            }
            if (figureMenuIsShown) {
                init();
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод");
            init();
        }
    }
}
