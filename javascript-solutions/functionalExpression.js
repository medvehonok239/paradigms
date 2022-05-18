'use strict'

/**
 Гораздо короче и проще сделать так:

 const binaryOp = f => (e1, e2) => {
    return (x, y, z) => f(e1(x, y, z), e2(x, y, z))
 }

 const add = binaryOp((a, b) => a + b)
 const subtract = binaryOp((a, b) => a - b);
 const multiply = binaryOp((a, b) => a * b);
 const divide = binaryOp((a, b) => a/ b);
 const negate = binaryOp(a => - a);
 */

const cnst = (value) => (_) => value;

const variable = name => function (x, y, z) {
    if (name.endsWith("x")) {
        return x;
    }

    if (name.endsWith("y")) {
        return y;
    }

    if (name.endsWith("z")) {
        return z;
    }
}

const add = (e1, e2) => function (x, y, z) {
    return e1(x, y, z) + e2(x, y, z);
}

const subtract = (e1, e2) => (x, y, z) => e1(x, y, z) - e2(x, y, z)

const multiply = (e1, e2) => (x, y, z) => e1(x, y, z) * e2(x, y, z)

const divide = (e1, e2) => (x, y, z) => e1(x, y, z) / e2(x, y, z)

const negate = e => (x, y, z) => -e(x, y, z)


// x^2 - 2*x + 1 === (x - 1) ^ 2
let ex = multiply(subtract(
        variable("x"), cnst(1)),
    subtract(variable("x"), cnst(1)));

for (let i = 0; i <= 10; i++) {
    console.log(ex(i, 0, 0));
}

let pi;
pi = cnst(Math.PI);

let e;
e = cnst(Math.E);
