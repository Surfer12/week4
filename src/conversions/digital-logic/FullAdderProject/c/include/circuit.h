#ifndef CIRCUIT_H
#define CIRCUIT_H

#include <stdbool.h>
#include "wire.h"

typedef struct {
    Wire* A;
    Wire* B;
    Wire* Cin;
    Wire* Sum;
    Wire* Cout;
} FullAdderCircuit;

FullAdderCircuit* createFullAdder();
void destroyFullAdder(FullAdderCircuit* fac);
void setInputs(FullAdderCircuit* fac, bool a, bool b, bool cin);
void evaluateCircuit(FullAdderCircuit* fac);
bool getSum(FullAdderCircuit* fac);
bool getCout(FullAdderCircuit* fac);

#endif