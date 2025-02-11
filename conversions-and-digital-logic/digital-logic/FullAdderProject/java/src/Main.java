import core.FullAdderCircuit;

public class Main {
    public static void main(String[] args) {
        // Quick interactive test
        FullAdderCircuit adder = new FullAdderCircuit();

        // Example: A=1, B=1, Cin=0 => expect Sum=0, Cout=1
        adder.setInputs(true, true, false);
        adder.evaluate();
        System.out.println("Sum:  " + adder.getSum());
        System.out.println("Cout: " + adder.getCout());
    }
}