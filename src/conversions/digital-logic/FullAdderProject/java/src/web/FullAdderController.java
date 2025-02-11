package web;

import core.FullAdderCircuit;
import database.Neo4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FullAdderController {
    private final FullAdderCircuit adder = new FullAdderCircuit();
    private final Neo4jLogger logger;

    public FullAdderController(@Value("${spring.neo4j.uri}") String uri,
                               @Value("${spring.neo4j.authentication.username}") String user,
                               @Value("${spring.neo4j.authentication.password}") String password) {
        this.logger = new Neo4jLogger(uri, user, password);
    }

    @PostMapping("/add")
    public String add(@RequestParam boolean A, @RequestParam boolean B, @RequestParam boolean Cin) {
        adder.setInputs(A, B, Cin);
        adder.evaluate(logger);
        return String.format("Sum: %b, Cout: %b", adder.getSum(), adder.getCout());
    }

    @PostMapping("/clearLog")
    public String clearLog() {
        logger.clearDatabase();
        return "Database Cleared!";
    }
}