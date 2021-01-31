package exercise1;

import exercise1.IMVC.IController;
import exercise1.Controllers.DBController;
import exercise1.model.DBLayer.MongoDBRepository;
import exercise1.IMVC.IModel;
import exercise1.View.ConsoleIView;
import exercise1.IMVC.IView;

/*
Задание№1

Создать приложение, которое сможет считывать из файла набор фигур (должно быть не менее 4 различных типов фигур, и при этом хоть один параметр общий).
Желательно использовать абстрактную фабрику.

Доступны должно быть следующие действия:
1) повернуть на
2) переместить на
3) увеличить/ уменьшить в
4) рассчитать площадь

По Вашему усмотрению программа может быть с выводом графическим ( консоль, графическое приложение, веб-проект), либо без (таким образом на вход файл - результат тоже файл)

**/
public class Main {
    public static void main(String[] args) {
        IModel model = new MongoDBRepository();
        IController controller = new DBController(model);
        IView view = new ConsoleIView(controller);
        view.init();
//        Menu.start();
    }
}
