#ifndef GATE_H
#define GATE_H

#include <stdbool.h>

typedef struct Wire Wire;

typedef struct Gate {
    Wire** inputs;
    int inputCount;
    Wire* output;
    void (*evaluate)(struct Gate*);
} Gate;

#endif