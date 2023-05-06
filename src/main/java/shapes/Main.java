package shapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("shapes-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("shapes.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Shapes API")
                .description("REST API for interaction with geometrical shapes")
                .version("1.0")
                .build();
    }
}
