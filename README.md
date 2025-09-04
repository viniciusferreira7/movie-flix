# ⚠️ WIP
# API Flix

REST API for managing a movie catalog and streaming services, built with Spring Boot and a modern architecture.

## About the Project

Flix is a platform that allows users to discover movies available across different streaming services. The project was developed with a focus on:

- **Content Organization**: Efficient categorization of movies
- **Multiple Services**: Integration with various streaming providers
- **Security**: JWT authentication to protect endpoints
- **Performance**: Optimized queries and caching for better performance
- **Scalability**: Architecture designed for growth

## Architecture

The project follows a layered architecture:

```

src/main/java/dev/flix/
├── config/         # Spring and Security configurations
├── controller/     # REST Controllers
├── entity/         # JPA Entities
├── repository/     # Spring Data Repositories
├── service/        # Business logic
├── exception/      # Custom exceptions
└── mapper/         # DTO <-> Entity conversions

````

## Technologies Used

### Backend
- **Java 17**: LTS version with modern language features
- **Spring Boot 3**: Framework for rapid development
- **Spring Security**: Security and authentication
- **Spring Data JPA**: Data persistence
- **JWT**: Stateless authentication tokens
- **Lombok**: Reduces boilerplate code
- **Bean Validation**: Data validation

### Database
- **PostgreSQL 15**: Robust relational database
- **Flyway**: Schema versioning and migrations

### Development Tools
- **Maven**: Dependency management and build tool
- **JUnit 5**: Unit testing
- **Mockito**: Mocking for tests
- **Swagger/OpenAPI**: API documentation

## Features

### Authentication & Authorization
- User registration and login system
- JWT-based authentication
- Role-based route protection

### Category Management
- Full CRUD for movie categories
- Data validation
- Dependency handling

### Streaming Services
- Register and manage providers
- Associate movies with providers
- Integrity validations

### Movie Catalog
- Detailed movie registration
- Search by multiple criteria
- Association with categories and services
- Rating system

## Requirements

- Java 17+
- PostgreSQL 15+
- Maven 3.8+

## How to Run

The project includes scripts to simplify build and execution:

### Available Scripts

- `build.sh`: Builds the project and generates the JAR file
- `start.sh`: Starts the application using the generated JAR

### Step by Step

1. Clone the repository
```bash
git clone [repository-url]
````

2. Configure PostgreSQL in the `application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flix
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:

```bash
./build.sh
```

4. Start the application:

```bash
./start.sh
```

Alternatively, you can run manually:

```bash
# Manual build
mvn clean package

# Manual run
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Documentation

All routes are fully documented with **Swagger/OpenAPI** and can be accessed at:

- `http://localhost:8080/swagger-ui.html`

### Endpoints

#### Authentication

* POST `/auth/register` - Register new user
* POST `/auth/login` - User login

#### Categories

* POST `/flix/category` - Create category
* GET `/flix/category` - List categories
* GET `/flix/category/{id}` - Get category by ID
* DELETE `/flix/category/{id}` - Delete category

#### Streaming Services

* POST `/flix/stream-service` - Create service
* GET `/flix/stream-service` - List services
* GET `/flix/stream-service/{id}` - Get service by ID
* DELETE `/flix/stream-service/{id}` - Delete service

#### Movies

* POST `/flix/movie` - Create movie
* GET `/flix/movie` - List movies
* GET `/flix/movie/{id}` - Get movie by ID
* GET `/flix/movie/search?category={id}` - Search movies by category
* PUT `/flix/movie` - Update movie
* DELETE `/flix/movie/{id}` - Delete movie

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Authors

* **Vinicius Ferreira**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
