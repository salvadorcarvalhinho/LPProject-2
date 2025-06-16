let double: int -> int =
    fn x:int => { x * 2 };

let square: int -> int =
    fn x:int => { x * x };

let add_ten: int -> int =
    fn x:int => { x + 10 };

/* Note: Complex function types may not be fully supported, using simpler approach */
let test_compose: int -> int =
    fn x:int => { double(square(x)) };

let test_apply_twice: int -> int =
    fn x:int => { double(double(x)) };

let map_to_pair: int -> int -> struct{#first:int, #second:int} =
    fn a:int, b:int => {
        {
            #first = add_ten(a),
            #second = add_ten(b)
        }
    };

let test_val = 3;
let doubled = double(test_val);
let squared = square(test_val);
let composed = test_compose(test_val);
let twice_doubled = test_apply_twice(test_val);
let pair_result = map_to_pair(5)(15);

println(doubled);
println(squared);
println(composed);
println(twice_doubled);
println(pair_result.#first);
println(pair_result.#second);;