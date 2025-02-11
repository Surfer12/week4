#include <stdlib.h>
#include "gate.h"
#include "wire.h"

void evaluateAND(Gate* g) {
    bool result = true;
    for (int i = 0; i < g->inputCount; i++) {
        result &= g->inputs[i]->value;
    }
    g->output->value = result;
}

void evaluateXOR(Gate* g) {
    bool result = false;
    for (int i = 0; i < g->inputCount; i++) {
        result ^= g->inputs[i]->value;
    }
    g->output->value = result;
}

void evaluateOR(Gate* g) {
    bool result = false;
    for (int i = 0; i < g->inputCount; i++) {
        result |= g->inputs[i]->value;
    }
    g->output->value = result;
}