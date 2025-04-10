# book-service
Инструкция по запуску

   • Предварительно создать БД POSTGRES не ниже версии 15 с именем "books-db".

   • Поменять в application.yaml spring.datasource.username и spring.datasource.password на свои логин и пароль от PostgreSQL.

   • Скомпилировать jar файл с помощью maven командой `mvn clean install`.

   • Запустить приложение командой java `java -jar book-service-1.0.0-SNAPSHOT.jar`.
   
Для теста in-memory добавлен пользователь Admin, пароль root.
