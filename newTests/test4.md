type CharNode = struct {
    #value: int,
    #hasnext: bool,
    #nextval: int
};
type SimpleString = union {
    #empty: (),
    #single: int,
    #double: struct { #first: int, #second: int },
    #triple: struct { #first: int, #second: int, #third: int }
};
let char_a = 65;
let char_b = 66;
let char_c = 67;
let make_triple_string: int -> int -> int -> SimpleString =
    fn c1:int, c2:int, c3:int => {
        #triple({
            #first = c1,
            #second = c2,
            #third = c3
        })
    };
let string_length: SimpleString -> int =
    fn s:SimpleString => {
        match s {
            #empty(_) -> 0
        |   #single(_) -> 1
        |   #double(_) -> 2
        |   #triple(_) -> 3
        }
    };
let find_char_in_triple: SimpleString -> int -> bool =
    fn s:SimpleString, target:int => {
        match s {
            #empty(_) -> false
        |   #single(c) -> c == target
        |   #double(pair) -> 
                (pair.#first == target) || (pair.#second == target)
        |   #triple(trip) ->
                (trip.#first == target) || 
                (trip.#second == target) || 
                (trip.#third == target)
        }
    };
let is_ascending_triple: SimpleString -> bool =
    fn s:SimpleString => {
        match s {
            #empty(_) -> true
        |   #single(_) -> true
        |   #double(pair) -> pair.#first <= pair.#second
        |   #triple(trip) ->
                (trip.#first <= trip.#second) && 
                (trip.#second <= trip.#third)
        }
    };
let get_first_char: SimpleString -> int =
    fn s:SimpleString => {
        match s {
            #empty(_) -> -1
        |   #single(c) -> c
        |   #double(pair) -> pair.#first
        |   #triple(trip) -> trip.#first
        }
    };
let test_string = make_triple_string(char_a)(char_b)(char_c);
let reverse_string = make_triple_string(char_c)(char_b)(char_a);
let mixed_string = make_triple_string(char_b)(char_a)(char_c);
println(string_length(test_string));
println(find_char_in_triple(test_string)(char_b));
println(find_char_in_triple(test_string)(68));
println(is_ascending_triple(test_string));
println(is_ascending_triple(reverse_string));
println(is_ascending_triple(mixed_string));
println(get_first_char(test_string));;