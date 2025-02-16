from gates import Wire, XORGate, ANDGate, ORGate

struct FullAdder:
    var a: Wire
    var b: Wire
    var cin: Wire
    var sum: Wire
    var cout: Wire

    var xor1: XORGate
    var xor2: XORGate
    var and1: ANDGate
    var and2: ANDGate
    var or1: ORGate

    fn __init__():
        a = Wire(false)
        b = Wire(false)
        cin = Wire(false)

        sum = Wire(false)
        cout = Wire(false)

        xor1 = XORGate(a, b, Wire(false))
        xor2 = XORGate(xor1.output, cin, sum)

        and1 = ANDGate(a, b, Wire(false))
        and2 = ANDGate(cin, xor1.output, Wire(false))
        or1 = ORGate(and1.output, and2.output, cout)

    fn evaluate():
        xor1.evaluate()
        xor2.evaluate()
        and1.evaluate()
        and2.evaluate()
        or1.evaluate()