package shapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* todo:
* + 0) Поправить структуру проекта(убрать логику из old)
* + 1) Перейти на реляционную бд
* + 2) подключить spring jpa
* + 3) использовать dto
* +-4) добавить тесты и jacoco
* + 5) добавить checkstyle
* + 6) прикрутить swagger
* +-7) Добавить проверку на корректность входящих данных
* + 8) Прикрутить действия над фигурами (поворот, перемещение, масштабирование, расчет площади)
* + 9) Создать readme.md
* */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
