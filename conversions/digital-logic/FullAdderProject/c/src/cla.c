#include "cla.h"

// Function to calculate Generate signal (Gi = Ai AND Bi)
bool calculateGenerate(bool a, bool b) {
    return a && b;
}

// Function to calculate Propagate signal (Pi = Ai XOR Bi)
bool calculatePropagate(bool a, bool b) {
    return a ^ b;
}

// Function to calculate carry signals for 4-bit CLA
void calculateCarries(bool c0, bool *g, bool *p, bool *carries) {
    carries[0] = g[0] || (p[0] && c0); // C1
    carries[1] = g[1] || (p[1] && g[0]) || (p[1] && p[0] && c0); // C2
    carries[2] = g[2] || (p[2] && g[1]) || (p[2] && p[1] && g[0]) || (p[2] && p[1] && p[0] && c0); // C3
    carries[3] = g[3] || (p[3] && g[2]) || (p[3] && p[2] && g[1]) || (p[3] && p[2] && p[1] && g[0]) || (p[3] && p[2] && p[1] && p[0] && c0); // C4
}

// Function for 4-bit CLA addition
void cla_4bit_add(bool *a, bool *b, bool cin, bool *sum, bool *cout) {
    bool g[4], p[4], carries[4];

    // Calculate Generate and Propagate signals
    for (int i = 0; i < 4; i++) {
        g[i] = calculateGenerate(a[i], b[i]);
        p[i] = calculatePropagate(a[i], b[i]);
    }

    // Calculate carry signals
    calculateCarries(cin, g, p, carries);

    // Calculate sum bits
    sum[0] = p[0] ^ cin;
    sum[1] = p[1] ^ carries[0];
    sum[2] = p[2] ^ carries[1];
    sum[3] = p[3] ^ carries[2];

    // Carry-out is the last carry calculated (C4)
    *cout = carries[3];
}