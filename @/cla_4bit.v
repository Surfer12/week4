// Verilog implementation of a 4-bit Carry Lookahead Adder (CLA)

module cla_4bit(
    input [3:0] A,      // 4-bit input A
    input [3:0] B,      // 4-bit input B
    input C_in,         // Carry-in

    output [3:0] Sum,   // 4-bit Sum output
    output C_out        // Carry-out
);

// Generate and Propagate signals for each bit
wire [3:0] P, G;
assign P = A ^ B;      // Propagate signal Pi = Ai XOR Bi
assign G = A & B;      // Generate signal Gi = Ai AND Bi

// Carry signals using Carry Lookahead logic
wire C1, C2, C3, C4;
assign C1 = G[0] | (P[0] & C_in);
assign C2 = G[1] | (P[1] & G[0]) | (P[1] & P[0] & C_in);
assign C3 = G[2] | (P[2] & G[1]) | (P[2] & P[1] & G[0]) | (P[2] & P[1] & P[0] & C_in);
assign C4 = G[3] | (P[3] & G[2]) | (P[3] & P[2] & G[1]) | (P[3] & P[2] & P[1] & G[0]) | (P[3] & P[2] & P[1] & P[0] & C_in);

// Sum bits
assign Sum[0] = P[0] ^ C_in;
assign Sum[1] = P[1] ^ C1;
assign Sum[2] = P[2] ^ C2;
assign Sum[3] = P[3] ^ C3;

// Carry-out
assign C_out = C4;

endmodule