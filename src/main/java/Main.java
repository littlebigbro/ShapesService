import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

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

/*
* 0) Поправить структуру проекта
* 1) Перейти на реляционную бд
* 2) подключить spring jpa
* 3) использовать dto
* 4) добавить тесты и jacoco
* 5) добавить checkstyle
*
* */
@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
