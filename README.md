# Price calculator

## Descripción del Proyecto

Este proyecto es una servicio desarrollado en Spring Boot que proporciona endpoints REST para realizar consultas sobre
precios en una base de datos de comercio electrónico. La aplicación acepta parámetros de entrada, como la fecha de
aplicación, el identificador de producto y el identificador de cadena, y devuelve información relevante sobre la tarifa
a aplicar, fechas de aplicación y precio final.

## Características Principales

- **Tecnología:**
    - Desarrollado en Spring Boot.


- **Base de Datos:**
    - Utiliza una base de datos en memoria H2 sqlite (para una fácil prueba local sin perder los datos de prueba después
      de cada reinicio), se puede cambiar fácilmente en el `application.properties` para cualquier otra base de datos.


- **Datos de Ejemplo:**
    - Inicializa la base de datos con datos de ejemplo para ilustrar su funcionamiento.


- **Endpoint REST:**
    - Se suministra `curl` HTTP GET con un cuerpo JSON y encabezados.

         ```bash
          curl -X GET http://{server}/api/proce-calculator/price \
               -H "Content-Type: application/json" \
               -d '{"producto_id": "value1", "brand_id": "value2", "start_date": "value3"}'

    - Se agrega la documentacion al servicio por swagger que puede ser consultada por medio del siguiente link
    
         ```bash
          http://localhost:8080/swagger-ui/index.html

## Configuración y Ejecución

1. **Clonar el Repositorio:**
   ```bash
   git clone git@github.com:01dalkon/PriceCalculator.git

2. **Cómo empezar:**
    - Necesitarás Java 17 instalado.

       ```bash
       ./gradlew bootRun

    - Para comprobar que funciona, abra una pestaña del navegador
      en `http://localhost:8080/api/proce-calculator/health` .
      También puede ejecutar

        ```bash
        curl http://localhost:8080/api/proce-calculator/health

    - Pruébelo con Docker

      ```bash
      ./gradlew bootBuildImage --imageName spring-boot-realworld-example-app
      docker run -p 8081:8080 spring-boot-realworld-example-app

2. **Ejecución de test:**
    - El repositorio contiene casos de prueba para cubrir tanto la prueba de la api como la prueba del repositorio.

   ```bash
   ./gradlew test
