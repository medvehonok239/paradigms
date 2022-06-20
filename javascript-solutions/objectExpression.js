"use strict"

function getAns(a, b, sign) {
    return a.toString() + " " + b.toString() + " " + sign
}

function BinOp(a, b, f) {
    this.a = a;
    this.b = b;

    this.evaluate = (f) = (x, y, z) => f(this.a.evaluate(x, y, z), this.b.evaluate(x, y, z));
    this.toString = () => {
    };
    this.prefix = () => {
    };
}

let pr = {
    "+": 2,
    "-": 2,
    "/": 2,
    "*": 2,
    "negate": 1,
    "sinh": 1,
    "cosh": 1
}

function Add(a, b) {
    BinOp.call(this, a, b, (a, b) => a + b);

    this.evaluate = (x, y, z) => this.a.evaluate(x, y, z) + this.b.evaluate(x, y, z);
    this.toString = () => getAns(this.a, this.b, "+");
    this.prefix = () => "(+ " + a.prefix() + " " + b.prefix() + ")";
}

function Subtract(a, b) {
    BinOp.call(this, a, b);

    this.evaluate = (x, y, z) => this.a.evaluate(x, y, z) - this.b.evaluate(x, y, z);
    this.toString = () => getAns(this.a, this.b, "-");
    this.prefix = () => "(- " + this.a.prefix() + " " + this.b.prefix() + ")";
}

function Multiply(a, b) {
    BinOp.call(this, a, b);

    this.evaluate = (x, y, z) => this.a.evaluate(x, y, z) * this.b.evaluate(x, y, z);
    this.toString = () => getAns(this.a, this.b, "*");
    this.prefix = () => "(* " + this.a.prefix() + " " + this.b.prefix() + ")";
}

function Divide(a, b) {
    BinOp.call(this, a, b);

    this.evaluate = (x, y, z) => this.a.evaluate(x, y, z) / this.b.evaluate(x, y, z);
    this.toString = () => getAns(this.a, this.b, "/");
    this.prefix = () => "(/ " + this.a.prefix() + " " + this.b.prefix() + ")";
}

class Const {
    constructor(e) {
        this.e = e;
    }

    evaluate = () => this.e;
    toString = () => this.e.toString();
    prefix = () => this.e.toString();
}

function Negate(e) {
    this.evaluate = (x, y, z) => -e.evaluate(x, y, z);
    this.toString = () => e.toString() + " negate";
    this.prefix = () => "(negate " + e.prefix() + ")";
}

class Sinh {
    constructor(e) {
        this.e = e;
    }

    evaluate = (x, y, z) => Math.sinh(this.e.evaluate(x, y, z));
    toString = () => this.e.toString() + " sinh";
    prefix = () => "(sinh " + this.e.prefix() + ")";
}

class Cosh {
    constructor(e) {
        this.e = e;
    }

    evaluate = (x, y, z) => Math.cosh(this.e.evaluate(x, y, z));
    toString = () => this.e.toString() + " cosh";
    prefix = () => "(cosh " + this.e.prefix() + ")";
}

class Min3 {
    constructor(a, b, c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    evaluate = (x, y, z) => Math.min(this.a.evaluate(x, y, z),
        this.b.evaluate(x, y, z),
        this.c.evaluate(x, y, z));

    toString = () => this.a.toString() + " "
        + this.b.toString() + " "
        + this.c.toString() + " min3"
}

class Max5 {
    constructor(a, b, c, d, e) {
        this.a = a
        this.b = b
        this.c = c
        this.d = d
        this.e = e
    }

    evaluate = (x, y, z) => Math.max(this.a.evaluate(x, y, z),
        this.b.evaluate(x, y, z),
        this.c.evaluate(x, y, z),
        this.d.evaluate(x, y, z),
        this.e.evaluate(x, y, z))

    toString = () => this.a.toString() + " "
        + this.b.toString() + " "
        + this.c.toString() + " "
        + this.d.toString() + " "
        + this.e.toString() + " max5";
}

function Variable(name) {
    this.evaluate = (x, y, z) => {
        switch (name) {
            case "x" :
                return x;
            case "y" :
                return y;
            case "z" :
                return z;
            default:
                break;
        }
    }

    this.toString = () => name;
    this.prefix = () => name;
}

function parse(string) {
    let stack = [];

    for (let sign of string.trim().split(/\s+/)) {
        let flag_BIN_OP = false;
        let flag_VAR = false;
        let flag_MINMAX = false;

        if (sign === "x" || sign === "y" || sign === "z") {
            stack.push(new Variable(sign))
            flag_VAR = true;
        }

        if (sign === "+") {
            stack.push(new Add(...stack.splice(-2)))
            flag_BIN_OP = true;
        }

        if (sign === "-") {
            stack.push(new Subtract(...stack.splice(-2)))
            flag_BIN_OP = true;
        }

        if (sign === "/") {
            stack.push(new Divide(...stack.splice(-2)))
            flag_BIN_OP = true;
        }

        if (sign === "*") {
            stack.push(new Multiply(...stack.splice(-2)))
            flag_BIN_OP = true;
        }

        if (sign === "cosh") {
            stack.push(new Cosh(...stack.splice(-1)))
            flag_BIN_OP = true;
        }

        if (sign === "min3") {
            stack.push(new Min3(...stack.splice(-3)))
            flag_MINMAX = true
        }

        if (sign === "max5") {
            stack.push(new Max5(...stack.splice(-5)))
            flag_MINMAX = true
        }

        if (sign === "sinh") {
            stack.push(new Sinh(...stack.splice(-1)))
            flag_BIN_OP = true;
        }

        if (sign === "negate") {
            stack.push(new Negate(...stack.splice(-1)))
            flag_BIN_OP = true;
        }

        if (!flag_BIN_OP && !flag_VAR && !flag_MINMAX) {
            stack.push(new Const(parseInt(sign)))
        }
    }

    return stack.pop()
}

const ops = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
    "negate": Negate,
    "sinh": Sinh,
    "cosh": Cosh
}

function parsePrefix(str) {
    let hasBr = false;
    let operator = false;
    let balance = 0

    str.split("").forEach(s => {
        if (s === "(") balance++; else if (s === ")") balance--;
        if (balance < 0) {
            throw new Error("Missing (")
        }
        if (balance !== 0) {
            hasBr = true;
        }
    })
    if (balance !== 0) {
        throw new Error("Missing )")
    }
    str = str.replaceAll(")", " ")
        .replaceAll("(", " ")
        .split(" ")
        .filter(s => s !== "")

    str = str.reverse()

    let stack = []
    for (let i = 0; i < str.length; i++) {
        if (str[i] in ops) {
            if (stack.length < pr[str[i]]) {
                throw new Error();
            }
            operator = true;
            stack.push(new ops[str[i]](...stack.splice(-pr[str[i]]).reverse()))
        } else {
            let arg = str[i]
            if (["x", "y", "z"].includes(str[i])) {
                arg = new Variable(str[i])
            } else if (isConst(str[i])) {
                arg = new Const(parseInt(str[i]))
            } else {
                throw new Error("Invalid data input : " + str[i])
            }

            stack.push(arg)
        }
    }
    if (stack.length === 0) {
        throw new Error("Empty")
    }

    if (stack.length !== 1) {
        throw new Error("Invalid argument : " + stack[0])
    }
    if (hasBr && !operator) {
        throw new Error("Invalid unary/binary ");
    }
    return stack.pop()
}

function isConst(string) {
    let count = 0;
    for (let i = 0; i < string.length; i++) {
        if (string[i] >= '0' && string[i] <= '9' || string[i] === "-") {
            count++;
        }
    }
    return count === string.length
}
