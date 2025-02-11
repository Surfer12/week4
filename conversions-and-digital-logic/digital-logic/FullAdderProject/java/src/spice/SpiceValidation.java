package spice;

import java.io.*;
import java.util.*;
import com.opencsv.*; // Example for CSV handling

/**
 * Validation script to compare SPICE simulation outputs with expected results
 */
public class SpiceValidation {

    private String spiceOutputFile;
    private List<Map<String, String>> expectedResults;

    public SpiceValidation(String spiceOutputFile, List<Map<String, String>> expectedResults) {
        this.spiceOutputFile = spiceOutputFile;
        this.expectedResults = expectedResults;
    }

    public boolean validate() {
        try (BufferedReader reader = new BufferedReader(new FileReader(spiceOutputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (Map<String, String> expectedEntry : expectedResults) {
                    for (Map.Entry<String, String> entry : expectedEntry.entrySet()) {
                        if (line.contains(entry.getKey())) {
                            String outputValue = parseOutputValue(line);
                            if (!outputValue.equals(entry.getValue())) {
                                System.out.printf("Validation failed! Expected %s = %s but got %s\n", entry.getKey(), entry.getValue(), outputValue);
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Validation succeeded! All outputs match the expected results.");
        return true;
    }

    private String parseOutputValue(String line) {
        // Assuming simulation output like "SUM=1" or "COUT=0"
        return line.split("=")[1].trim();
    }

    public static List<Map<String, String>> loadFromCSV(String filePath) {
        List<Map<String, String>> testCases = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext(); // Read headers
            String[] line;
            while ((line = reader.readNext()) != null) {
                Map<String, String> testCase = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    testCase.put(headers[i], line[i]);
                }
                testCases.add(testCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCases;
    }

    public static void main(String[] args) {
        String spiceOutputPath = "/Users/ryanoates/Desktop/lab2schematixspice/netlist_exports/simulation_output.sp";
        String expectedResultsCSV = "/Users/ryanoates/Desktop/lab2schematixspice/expected_results.csv";

        // Load expected results from CSV file
        List<Map<String, String>> expectedList = loadFromCSV(expectedResultsCSV);

        // Create and run the validator
        SpiceValidation validator = new SpiceValidation(spiceOutputPath, expectedList);
        boolean success = validator.validate();

        if (success) {
            System.out.println("Validation process completed successfully.");
        } else {
            System.out.println("Validation process failed. Check output logs for more details.");
        }
    }
}