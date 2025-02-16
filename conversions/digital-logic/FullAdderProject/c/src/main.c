#include <stdio.h>
#include <stdbool.h>
#include "circuit.h"
#include "cla.h"

int main() {
    FullAdderCircuit* fac = createFullAdder();

    bool inputs[8][3] = {
        {false, false, false},
        {false, false, true},
        {false, true, false},
        {false, true, true},
        {true, false, false},
        {true, false, true},
        {true, true, false},
        {true, true, true}
    };

    for (int i = 0; i < 8; i++) {
        bool A = inputs[i][0], B = inputs[i][1], Cin = inputs[i][2];
        setInputs(fac, A, B, Cin);

        printf("A=%d B=%d Cin=%d => Sum=%d Cout=%d\n", A, B, Cin, getSum(fac), getCout(fac));
    }

    destroyFullAdder(fac);

    printf("\n--- Carry Lookahead Adder (CLA) 4-bit Test ---\n");

    bool a_inputs[16][4] = { /* ... (Define 4-bit input patterns for A) ... */ };
    bool b_inputs[16][4] = { /* ... (Define 4-bit input patterns for B) ... */ };
    bool cin_inputs[16] = { /* ... (Define carry-in values) ... */ };

    for (int i = 0; i < 16; i++) {
        bool a[4] = {a_inputs[i][0], a_inputs[i][1], a_inputs[i][2], a_inputs[i][3]};
        bool b[4] = {b_inputs[i][0], b_inputs[i][1], b_inputs[i][2], b_inputs[i][3]};
        bool cin = cin_inputs[i];
        bool sum[4];
        bool cout;

        cla_4bit_add(a, b, cin, sum, &cout);

        printf("CLA: A=%d%d%d%d, B=%d%d%d%d, Cin=%d => Sum=%d%d%d%d, Cout=%d\n",
               a[3], a[2], a[1], a[0], b[3], b[2], b[1], b[0], cin,
               sum[3], sum[2], sum[1], sum[0], cout);
    }

    return 0;
}