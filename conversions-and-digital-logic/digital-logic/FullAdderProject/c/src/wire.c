#include <stdlib.h>
#include "wire.h"

void setWireValue(Wire* w, bool newVal) {
    if (w->value != newVal) {
        w->value = newVal;
        for (int i = 0; i < w->consumerCount; i++) {
            w->consumers[i]->evaluate(w->consumers[i]);
        }
    }
}