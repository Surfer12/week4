`timescale 1ns/1ps

module half_adder_tb;

  logic a, b;
  logic sum, carry;

  // Instantiate the basic half adder
  half_adder_basic dut_basic (
      .a(a),
      .b(b),
      .sum(sum),
      .carry(carry)
  );

  // Instantiate the nand half adder
    half_adder_nand dut_nand (
        .a(a),
        .b(b),
        .sum(sum),
        .carry(carry)
  );

  initial begin
    $dumpfile("half_adder_tb.vcd");
    $dumpvars(0, half_adder_tb);

    // Test all input combinations
    a = 0; b = 0; #20;
    if (sum !== 0 || carry !== 0) $error("Failed test case: a=0, b=0");

    a = 0; b = 1; #20;
    if (sum !== 1 || carry !== 0) $error("Failed test case: a=0, b=1");

    a = 1; b = 0; #20;
    if (sum !== 1 || carry !== 0) $error("Failed test case: a=1, b=0");

    a = 1; b = 1; #20;
    if (sum !== 0 || carry !== 1) $error("Failed test case: a=1, b=1");

    $display("All test cases passed!");
    $finish;
  end

endmodule
