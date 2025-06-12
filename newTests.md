// Test 1: Basic lazy vs strict list construction and evaluation
// Expected output:
// 1::2::3::nil
// 1
// [unevaluated] LazyCons
// [unevaluated] LazyCons

let strict = 1::2::3::nil;
let lazy = 1:?2:?3:?nil;
println strict;
match lazy {
    nil -> println (0)
|   a::b -> println (a); println (b)
};;


// Test 2: Lazy evaluation with function calls
// Expected output:
// 5
// 4
// 4

let mkll = fn n => {
    if (n == 0) {
        nil
    } else {
        n:?(mkll(n-1))
    }
};
let ll5 = mkll(5);
match ll5 {
    nil -> println (0)
|   a::b -> match b {
        nil -> println (a)
    |   c::d -> println (a); println (c)
    }
};;


// Test 3: Mixed lazy and strict cons operations
// Expected output:
// 10
// 20
// 20

let base = 10:?20::nil;
match base {
    nil -> println (0)
|   x::y -> println (x); match y {
        nil -> println (99)
    |   z::w -> println (z)
    }
};;


// Test 4: Simple infinite lazy sequence
// Expected output:
// 1
// 1
// 1
// 1

let ones = fn dummy => { 1:?(ones(0)) };
let infiniteOnes = ones(0);
match infiniteOnes {
    nil -> println (0)
|   a::b -> println a; match b {
        nil -> println (0)  
    |   c::d -> println c; match d {
            nil -> println (0)
        |   e::f -> println (e)
        }
    }
};;