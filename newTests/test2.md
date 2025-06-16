type Counter = struct {
    #count: ref<int>,
    #step: int
};

let create_counter: int -> Counter =
    fn step:int => {
        {
            #count = box 0,
            #step = step
        }
    };

let increment: Counter -> () =
    fn counter:Counter => {
        let count_ref = counter.#count;
        let current = !count_ref;
        count_ref := current + counter.#step;
        ()
    };

let decrement: Counter -> () =
    fn counter:Counter => {
        let count_ref = counter.#count;
        let current = !count_ref;
        count_ref := current - counter.#step;
        ()
    };

let get_count: Counter -> int =
    fn counter:Counter => {
        let count_ref = counter.#count;
        !count_ref
    };

let reset: Counter -> () =
    fn counter:Counter => {
        let count_ref = counter.#count;
        count_ref := 0;
        ()
    };

let my_counter = create_counter(5);
increment(my_counter);
increment(my_counter);
println(get_count(my_counter));
decrement(my_counter);
println(get_count(my_counter));
reset(my_counter);
println(get_count(my_counter));;