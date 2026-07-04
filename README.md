# 23-SB-DockerCompose

A Spring Boot application (Drinks Bar Management System) backed by MySQL, containerized and orchestrated with Docker Compose.

## Tech Stack

- **Java / Spring Boot** — application layer
- **Spring Data JPA / Hibernate** — persistence
- **MySQL 5.7** — database
- **Maven** — build tool
- **Docker & Docker Compose** — containerization and orchestration

## Project Structure

```
23-SB-DockerCompose/
├── src/
│   ├── main/
│   │   ├── java/org/example/       # Application source code
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/org/example/
│           └── ApplicationTests.java
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17+
- Maven 3.6+
- Docker
- Docker Compose (v1 `docker-compose` or v2 `docker compose` plugin)

## Configuration

Database connection is configured in `src/main/resources/application.properties`:

```properties
spring.application.name=23-SB-DockerCompose
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.url=jdbc:mysql://mysqldb:3306/sbms26
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> **Note:** The hostname `mysqldb` only resolves inside the Docker Compose network. It will not work if you run the app directly on your host machine — use `localhost` instead in that case.

## Building the Application

Build the JAR with Maven. Since the test suite currently requires a live MySQL connection to load the Spring context, skip tests during the build:

```bash
mvn clean package -DskipTests
```

This produces:

```
target/23-SB-DockerCompose-0.0.1-SNAPSHOT.jar
```

## Docker Setup

### Dockerfile

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /user/app
COPY target/23-SB-DockerCompose-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### docker-compose.yml

```yaml
version: "3.3"
services:
  application:
    image: spring-boot-drinksbar
    ports:
      - "8080:8080"
    networks:
      - spring-boot-net
    depends_on:
      - mysqldb
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysqldb:3306/sbms26
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
    volumes:
      - ./data/springboot-app:/data/springboot-app

  mysqldb:
    image: mysql:5.7
    networks:
      - spring-boot-net
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=sbms26
    volumes:
      - ./data/mysql:/var/lib/mysql

networks:
  spring-boot-net:
    driver: bridge
```

## Running the Project

1. **Build the application JAR:**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Build the Docker image:**
   ```bash
   docker build -t spring-boot-drinksbar .
   ```

3. **Start the containers:**
   ```bash
   docker-compose up -d
   ```
   or, if using Compose V2:
   ```bash
   docker compose up -d
   ```

4. **Verify containers are running:**
   ```bash
   docker-compose ps
   ```

5. **View application logs:**
   ```bash
   docker-compose logs -f application
   ```

6. **Access the application:**
   ```
   http://<ec2-public-ip>:8080
   ```

## Stopping the Project

```bash
docker-compose down
```

To also remove volumes (clears database data):

```bash
docker-compose down -v
```

## Known Issues & Notes

- **`contextLoads()` test failure:** The default `ApplicationTests` uses `@SpringBootTest`, which boots the full application context including Hibernate. Since MySQL isn't reachable during `mvn test` (outside the Compose network), this test fails with a Hibernate dialect error. Current workaround: build with `-DskipTests`. A more robust long-term fix is to add an H2 in-memory database for test scope so `mvn clean package` works without skipping tests.
- **Compose file version:** Older `docker-compose` (v1) binaries require an explicit `version` key (e.g., `"3.3"`). Newer Compose V2 (`docker compose`) ignores/doesn't require it.

## Roadmap / TODO

- [ ] Add H2 in-memory database for isolated unit/integration testing
- [ ] Add health checks for `mysqldb` in `docker-compose.yml`
- [ ] Add `.env` file support for credentials instead of hardcoding them
- [ ] Set up CI/CD pipeline (build → test → image push)

## License

Add your license here.
