module half_adder_nand (
    input  logic a,
    input  logic b,
    output logic sum,
    output logic carry
);

    logic nand1_out, nand2_out, nand3_out;
   
    //NAND Implementation
    nand(nand1_out, a, b);
    nand(nand2_out, a, b);
    nand(nand3_out, nand1_out, nand2_out);
    or (sum, nand3_out, nand3_out); //Using OR since my gate files include this
    and (carry, a, b);             //Using AND from the library files

endmodule
