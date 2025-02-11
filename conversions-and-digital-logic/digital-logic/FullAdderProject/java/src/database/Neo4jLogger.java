package database;

import org.neo4j.driver.*;

public class Neo4jLogger {
    private Driver driver;

    public Neo4jLogger(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void logInputsAndOutputs(boolean A, boolean B, boolean Cin, boolean Sum, boolean Cout) {
        try (Session session = driver.session()) {
            String query = "MERGE (a:Input {value: $A}) " +
                           "MERGE (b:Input {value: $B}) " +
                           "MERGE (cin:Input {value: $Cin}) " +
                           "MERGE (sum:Output {value: $Sum}) " +
                           "MERGE (cout:Output {value: $Cout}) " +
                           "MERGE (a)-[:CONTRIBUTES_TO]->(sum) " +
                           "MERGE (b)-[:CONTRIBUTES_TO]->(sum) " +
                           "MERGE (cin)-[:CONTRIBUTES_TO]->(sum) " +
                           "MERGE (a)-[:CONTRIBUTES_TO]->(cout) " +
                           "MERGE (b)-[:CONTRIBUTES_TO]->(cout) " +
                           "MERGE (cin)-[:CONTRIBUTES_TO]->(cout)";
            session.run(query, Values.parameters("A", A, "B", B, "Cin", Cin, "Sum", Sum, "Cout", Cout));
        }
    }

    public void clearDatabase() {
        try (Session session = driver.session()) {
            session.run("MATCH (n) DETACH DELETE n");
        }
    }

    public void close() {
        driver.close();
    }
}