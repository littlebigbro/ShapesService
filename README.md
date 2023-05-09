# Shapes Backend
[![codecov](https://codecov.io/gh/littlebigbro/ShapesService/branch/main/graph/badge.svg?token=T1STVGWTGQ)](https://codecov.io/gh/littlebigbro/ShapesService)<br/>
REST API приложение по взаимодействию с фигурами.</br>
В приложении реализованы:
1. CRUD для типов фигур
2. Для фигур:
   - CRUD
   - Получение площади фигуры 
   - Перемещение фигуры относительно центра
   - Поворот фигуры относительно центра
   - Масштабирование фигуры относитнльо центра<br/>
   
Настроен [swagger](http://localhost:8080/swagger-ui/)
</br>Команда для сборки проекта:
```bash
mvn clean checkstyle::check install spring-boot:run
```
Фронтенд ~~реализован~~ в проекте [shapesFront](https://github.com/littlebigbro/shapesFront).