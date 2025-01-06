Skypro graduate work: Back-end Ad Server routines Implementation

Author: Olga Krivenko

The Project is the backend part implementation for an ad service web application. The site, or front-end part is represented with the ready-to-use frontend part module running as a docker application. The interaction protocol between frontend and backend is specified with the openapi.yaml.

Project structure
The back-end application's architecture includes:

Controllers to handle requests getting from frontend
Services implementing data manipulation level
JpaRepositories - schemes that describe Object-Relation Management
Entities of ORM that include Ads, Comments to the ads and Users that are divided to ADMINs and USERs
DTO-s provide correspondence between backend Entities and frontend JSON data objects.
DTO and Responses statuses are specified within openapi.yaml.

Authentication
Authentication is configured with the config.WebSecurityConfig which provides the Application with the @Bean of a Password Encoder and filterChain-method.
A not authenticated user can view existing ads. An authenticated user can create ads and write comments and edit and delete created ads and comments. An authenticated user has their avatar image, ads also supposed to have their images.

Authorization
According to the specification in openapi.yaml users can have either the role ROLE_USER or ROLE_ADMIN. ADMIN is able to patch and delete any comment or ad when USER is allowed to patch and delete only their own entries. If a USER tries to patch or delete an ad or comment of other person then the status 403/Forbidden will be returned to the front.

Technologies used
Java 11;
Spring Boot;
Spring JPA;
Spring Security;
Springdoc;
SpringBootTest;
Docker;
Maven;
Lombok;
PostgreSQL;
Mapstruct.

How to build and launch
The project is developed as a spring-boot application, intended to be built with maven There is a pom.xml in the root directory. Below there are the steps describing how to build and run the application.

1.build the jar-file using the next command:
mvn clean install package [-DskipTests]

2.be sure your postgresql server is working. In case when there isn't a postgresql database server installed there is an option to launch it within a docker container, e.g.:
docker run -name habr-pg-13.3 -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -d postgres:13.3
In this example there are username, password and name of the database specified as 'postgres' for each of them.
Hence one can make a connection with the postgres server executing the command

psql -h localhost -U postgres

3.run the app:
java -jar target/ads-0.0.1-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/postgres --spring.datasource.username=postgres  --spring.datasource.password=postgres

4.There is an option to check functionality with the http-requests which are placed in the files in the http-requests directory.

5.Run the front end within docker container using the command:

docker run --rm -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:v1.21

6.Use in browser the next address to access the app:
http://localhost:3000/