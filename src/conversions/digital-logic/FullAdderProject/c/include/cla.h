#ifndef CLA_H
#define CLA_H

#include <stdbool.h>

// Function to calculate Generate signal
bool calculateGenerate(bool a, bool b);

// Function to calculate Propagate signal
bool calculatePropagate(bool a, bool b);

// Function to calculate carry signals for 4-bit CLA
void calculateCarries(bool c0, bool *g, bool *p, bool *carries);

// Function for 4-bit CLA addition
void cla_4bit_add(bool *a, bool *b, bool cin, bool *sum, bool *cout);

#endif // CLA_H