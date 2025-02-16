#include <stdlib.h>
#include <stdio.h>
#include "circuit.h"
#include "gate.h"
#include "wire.h"

static Gate* createGate(Wire** inputs, int inputCount, Wire* output, void (*evalFunc)(Gate*)) {
    Gate* g = (Gate*)malloc(sizeof(Gate));
    g->inputs = inputs;
    g->inputCount = inputCount;
    g->output = output;
    g->evaluate = evalFunc;
    for (int i = 0; i < inputCount; i++) {
        inputs[i]->consumers[inputs[i]->consumerCount++] = g;
    }
    return g;
}

static Wire* createWire() {
    Wire* w = (Wire*)malloc(sizeof(Wire));
    w->value = false;
    w->consumers = (Gate**)malloc(sizeof(Gate*) * 8);
    w->consumerCount = 0;
    return w;
}

FullAdderCircuit* createFullAdder() {
    FullAdderCircuit* fac = (FullAdderCircuit*)malloc(sizeof(FullAdderCircuit));
    fac->A = createWire();
    fac->B = createWire();
    fac->Cin = createWire();
    fac->Sum = createWire();
    fac->Cout = createWire();

    Wire* xor1Out = createWire();
    Wire* and1Out = createWire();
    Wire* and2Out = createWire();

    Wire* inputs1[2] = {fac->A, fac->B};
    createGate(inputs1, 2, xor1Out, evaluateXOR);

    Wire* inputs2[2] = {xor1Out, fac->Cin};
    createGate(inputs2, 2, fac->Sum, evaluateXOR);

    createGate(inputs1, 2, and1Out, evaluateAND);

    Wire* inputs3[2] = {fac->Cin, xor1Out};
    createGate(inputs3, 2, and2Out, evaluateAND);

    Wire* inputs4[2] = {and1Out, and2Out};
    createGate(inputs4, 2, fac->Cout, evaluateOR);

    return fac;
}

void destroyFullAdder(FullAdderCircuit* fac) {
    free(fac->A->consumers);
    free(fac->B->consumers);
    free(fac->Cin->consumers);
    free(fac->Sum->consumers);
    free(fac->Cout->consumers);
    free(fac->A);
    free(fac->B);
    free(fac->Cin);
    free(fac->Sum);
    free(fac->Cout);
    free(fac);
}

void setInputs(FullAdderCircuit* fac, bool a, bool b, bool cin) {
    setWireValue(fac->A, a);
    setWireValue(fac->B, b);
    setWireValue(fac->Cin, cin);
}

void evaluateCircuit(FullAdderCircuit* fac) {
}

bool getSum(FullAdderCircuit* fac) {
    return fac->Sum->value;
}

bool getCout(FullAdderCircuit* fac) {
    return fac->Cout->value;
}