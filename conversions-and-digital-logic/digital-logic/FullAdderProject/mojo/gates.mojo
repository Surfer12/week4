struct Wire:
    var value: Bool

fn set_value(wire: inout Wire, new_val: Bool):
    wire.value = new_val

struct Gate:
    fn evaluate(): None:
        pass

struct XORGate(Gate):
    var a: Wire
    var b: Wire
    var output: Wire

    fn evaluate():
        output.value = a.value ^ b.value

struct ANDGate(Gate):
    var a: Wire
    var b: Wire
    var output: Wire

    fn evaluate():
        output.value = a.value & b.value

struct ORGate(Gate):
    var a: Wire
    var b: Wire
    var output: Wire

    fn evaluate():
        output.value = a.value | b.value