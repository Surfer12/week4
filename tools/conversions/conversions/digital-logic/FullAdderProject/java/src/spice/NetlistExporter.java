package spice;

import core.FullAdderCircuit;
import core.Signal;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility to export circuits to SPICE-compatible netlists for LTSpice simulation.
 */
public class NetlistExporter {

    private FullAdderCircuit circuit;

    public NetlistExporter(FullAdderCircuit circuit) {
        this.circuit = circuit;
    }

    public String generateNetlist() {
        StringBuilder netlist = new StringBuilder();

        netlist.append("* SPICE Netlist for Full Adder\n");
        netlist.append(".include basic_gates.lib\n");
        netlist.append(".subckt FULLADDER A B CIN SUM COUT\n");
        
        // XOR1: A XOR B -> intermediate XOR output
        netlist.append("XOR1 N1 A B\n");
        // XOR2: Intermediate XOR output XOR CIN -> SUM
        netlist.append("XOR2 SUM N1 CIN\n");
        // AND1: A AND B -> intermediate carry 1
        netlist.append("AND1 N2 A B\n");
        // AND2: Intermediate XOR output AND CIN -> intermediate carry 2
        netlist.append("AND2 N3 N1 CIN\n");
        // OR: Combine intermediate carries -> COUT
        netlist.append("OR1 COUT N2 N3\n");
        netlist.append(".ends FULLADDER\n");

        return netlist.toString();
    }

    public void exportToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(generateNetlist());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FullAdderCircuit demoCircuit = new FullAdderCircuit(
            new Signal("A"), new Signal("B"), new Signal("CIN"), new Signal("SUM"), new Signal("COUT"));

        NetlistExporter exporter = new NetlistExporter(demoCircuit);
        String filePath = "/Users/ryanoates/Desktop/lab2schematixspice/netlist_exports/full_adder_netlist.sp";

        exporter.exportToFile(filePath);

        System.out.println("Netlist exported to: " + filePath);
    }
}