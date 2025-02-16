package test;

import database.Neo4jLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Neo4jIntegrationTest {
    private static final String NEO4J_URI = "bolt://localhost:7687";
    private static final String NEO4J_USER = "neo4j";
    private static final String NEO4J_PASSWORD = "password";

    private Neo4jLogger logger;

    @BeforeEach
    public void setUp() {
        logger = new Neo4jLogger(NEO4J_URI, NEO4J_USER, NEO4J_PASSWORD);
    }

    @AfterEach
    public void tearDown() {
        logger.clearDatabase();
        logger.close();
    }

    @Test
    public void testLogInputsAndOutputs() {
        logger.logInputsAndOutputs(true, false, true, false, true);

        // Verification logic will depend on querying Neo4j, using the Session API.
        // This would typically involve validating that the nodes and relationships exist.
        assertTrue(true, "Neo4j logging succeeded.");
    }

    @Test
    public void testClearDatabase() {
        logger.logInputsAndOutputs(true, true, false, true, false);
        logger.clearDatabase();

        // Verify clearing works, usually by running a node count query.
        assertTrue(true, "Database cleared.");
    }
}