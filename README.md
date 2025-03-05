# Logger

## Описание

Это проект для управления логированием событий в приложении. Он позволяет создавать записи журналов, а также хранить и обрабатывать их.

## Стек технологий

- Java 17
- Spring Boot
- PostgreSQL
- JUnit 5 (для тестирования)

## Установка

1. Склонируйте этот репозиторий на свой локальный компьютер:

   ```bash
   git clone [https://github.com/notherland/Chat.git](https://github.com/notherland/Logger)
   cd logger
   
2. Создайте базу данных, обновите ее подключение в application.properties или добавьте соответствующие переменные среды
   spring.datasource.username=${DB_USER}

   spring.datasource.url=${DB_URL}

   spring.datasource.password=${DB_PASSWORD}
3. Запустите с помощью

   mvn clean install

    mvn spring-boot:run
4. Приложение будет достпуно на http://localhost:8080/logger
