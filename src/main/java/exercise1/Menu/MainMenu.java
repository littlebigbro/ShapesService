package exercise1.Menu;

import exercise1.FigureHandler;

public class MainMenu {
    public static void start() {
        FigureHandler fh = new FigureHandler();
        fh.handle("/test.txt");
        System.out.println("Rectangle area - " + fh.getFigureList().get(2).calculateArea());
        System.out.println("Triangle area - " + fh.getFigureList().get(1).calculateArea());
        System.out.println("Circle area - " + fh.getFigureList().get(0).calculateArea());
    }
}
