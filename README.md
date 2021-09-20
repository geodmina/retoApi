# retoApi
Reto de programación Java

## Antes de iniciar
Antes de iniciar crear una base de datos de postgres llamada : reto

sudo -u postgres createdb reto

## Configurar base de datos
Configurar las propiedades de conexión en el aerchivo : 

src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:<puerto>/<dbBame>
spring.datasource.username=<usuario>
spring.datasource.password=<clave>
spring.jpa.show-sql=true

## Ejecución
Para ejecutar el proyecto ejecute el comando 

- mvn spring-boot:run

## Documentación
Para ver la doducmentación de las apis navegue a la siguiente ruta : 

- http://localhost:8080/swagger-ui/index.html