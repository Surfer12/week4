module half_adder_basic (
    input  logic a,
    input  logic b,
    output logic sum,
    output logic carry
);

    logic a_not, b_not, term1, term2;

    // Inverters
    not (a_not, a);
    not (b_not, b);

    // AND gates
    and (term1, a_not, b);
    and (term2, a, b_not);
    and (carry, a, b);

    // OR gate for Sum
    or  (sum, term1, term2);

endmodule
