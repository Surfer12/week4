# DigitalLogicToolkit

### Next Steps:
1. **Run the Tasks:**
   - Try running the tasks (`runNeo4jTest`, `serializeTest`, `runWebServer`) and verify their outputs.
2. **Dependency Installation:**
   - Ensure Neo4j database is properly configured and running at the specified address.
   - Ensure the dependent libraries (e.g., Kryo, Spring Boot) are successfully resolved during build.
3. **Custom Logic:**
   - Extend the logic inside each task as needed, based on your project requirements.
4. **Testing:**
   - Write and execute test cases to validate each task functionality.
## Project Overview
The `DigitalLogicToolkit` is a Java-based framework for working with digital logic, offering tools for logic design, simplification, and more. The framework also supports:

- Binary and decimal conversions.
- Truth table and K-Map generation/simplification.
- Gray Code conversion.
- Advanced integration with database frameworks and serialization utilities.

The project uses several modern Java frameworks and tools to extend functionality, including Spring MVC, Hibernate, and Neo4j, while supporting serialization and web-app frameworks.

---

## Build System
The project uses **Gradle**. The build file includes dependencies for:

- **JUnit 5**: For robust testing.
- **Mockito**: For mocking objects in tests.
- **Neo4j Driver**: For database graph representation and testing.
- **Hibernate and Spring JDBC**: For ORM and database access.
- **Serialization Libraries**: Support for **Kryo**, **SnakeYaml**, and **XStream**.
- **Spring MVC**: For building web applications.
- **Logback**: For robust logging.

To build the project:
```bash
gradle build
```

To run the tests:
```bash
gradle test
```

---

## Tasks
The following Gradle tasks have been implemented to extend project functionality:

### 1. **Generate Documentation (`generateDocs`)**
**Group**: `documentation`

Generates project documentation for classes and methods (currently a placeholder for doc generation logic).

```bash
gradle generateDocs
```

### 2. **Neo4j Database Test (`runNeo4jTest`)**
**Group**: `database`

Tests integration with a Neo4j database (logic implemented below).

```bash
gradle runNeo4jTest
```

### 3. **Serialization Validation (`serializeTest`)**
**Group**: `serialization`

Validates object serialization and deserialization with **Kryo**, **SnakeYaml**, and **XStream** libraries.

```bash
gradle serializeTest
```

### 4. **Run Spring MVC Web Server (`runWebServer`)**
**Group**: `web-app`

Runs a basic Spring MVC web server.

```bash
gradle runWebServer
```

---

## Framework Integration
### 1. **Neo4j Integration**
The framework integrates with Neo4j using the `neo4j-java-driver`. To test database interactions, a Neo4j test is executed in the `runNeo4jTest` task.

Sample Code:
```java
import org.neo4j.driver.*;

public class Neo4jExample {
    public void testConnection() {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(tx -> {
                Result result = tx.run("CREATE (a:Greeting) SET a.message = $message RETURN a.message + ', from node ' + id(a)",
                        Values.parameters("message", "hello, world"));
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        } finally {
            driver.close();
        }
    }
}
```

### 2. **Serialization**
Serialization support uses **Kryo**, **SnakeYaml**, and **XStream** for flexibility in serializing/deserializing objects.

Sample Code:
```java
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;

public class SerializationExample {
    public void testKryoSerialization() {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));
        Input input = new Input(new FileInputStream("file.bin"));

        MyObject object = new MyObject("test", 42);
        kryo.writeObject(output, object);
        output.close();

        MyObject deserialized = kryo.readObject(input, MyObject.class);
        input.close();

        System.out.println("Deserialized Object: " + deserialized);
    }
}

class MyObject implements Serializable {
    private String name;
    private int value;

    public MyObject(String name, int value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public String toString() {
        return "MyObject{name='" + name + "', value=" + value + '}';
    }
}
```

### 3. **Spring MVC Web Server**
The web server uses Spring MVC to serve HTTP requests.

Sample Code for `runWebServer` Task:
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringMvcApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApp.class, args);
    }

    @RestController
    class GreetingController {

        @GetMapping("/greet")
        public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
            return "Hello, " + name + "!";
        }
    }
}
```

To run the application:
```bash
gradle runWebServer
```

Navigate to:
```plaintext
http://localhost:8080/greet?name=YourName
```
---

## Logging
The project leverages **Logback** for structured logging. Modify the `logback.xml` file to configure logging.

---

## Best Practices
### Modular Design
- Classes are designed with **single responsibility** principles.
- Framework integrations are implemented as independent modules.

### Testing
- Every feature is tested using **JUnit 5**.
- Mock dependencies where applicable using **Mockito**.

### Serialization
- Supports multiple formats for flexibility.

### Web Framework
- Spring MVC provides a strong foundation for serving web applications.

---

## Contribution
Feel free to fork the repository and create pull requests. Contributions are welcomed!

---