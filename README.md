Weather Application back-end using OpenWeatherMap API

API documentation created with Swagger.


To access OpenWeatherMap API, you first need to generate an API key from your account.

This API key shall be inserted into resources/application.properties for the value "owm.apikey".

To start the server and application, excecute the main() method in src/main/java/WeatherApp/WeatherAppApplication.java.

Memory-based H2 database with default authentication is used for data storage.

Database schema is created during runtime via resources/schema.sql and some sample data is inserted via resources/data.sql.

Current weather data is automatically updated for every city in the database after every 15 minutes.

To view the API documentation, head over to http://localhost:8080/swagger-ui/#/weather-app-controller

The endpoints are described under "weather-app-controller".
