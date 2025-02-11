#include <stdio.h>
#include "circuit.h"

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
    return 0;
}