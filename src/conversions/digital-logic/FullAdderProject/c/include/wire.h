#ifndef WIRE_H
#define WIRE_H

#include <stdbool.h>
#include "gate.h"

typedef struct Wire {
    bool value;
    Gate** consumers;
    int consumerCount;
} Wire;

#endif