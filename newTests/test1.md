/* Binary Tree operations test */
type TreeNode = struct {
    #value: int,
    #left: Tree,
    #right: Tree
};
type Tree = union {
    #empty: (),
    #node: TreeNode
};

/* Helper function to create a leaf node */
let leaf: int -> Tree =
    fn val:int => {
        let node:TreeNode = {
            #value = val,
            #left = #empty(()),
            #right = #empty(())
        };
        #node(node)
    };

/* Insert function for binary search tree */
let insert: Tree -> int -> Tree =
    fn tree:Tree, val:int => {
        match tree {
            #empty(_) -> leaf(val)
        |   #node(n) -> 
                if (val < n.#value) {
                    #node({
                        #value = n.#value,
                        #left = insert(n.#left)(val),
                        #right = n.#right
                    })
                } else {
                    #node({
                        #value = n.#value,
                        #left = n.#left,
                        #right = insert(n.#right)(val)
                    })
                }
        }
    };

/* Search function */
let search: Tree -> int -> Tree =
    fn tree:Tree, target:int => {
        match tree {
            #empty(_) -> #empty(())
        |   #node(n) ->
                if (n.#value == target) {
                    tree
                } else {
                    if (target < n.#value) {
                        search(n.#left)(target)
                    } else {
                        search(n.#right)(target)
                    }
                }
        }
    };

/* Check if tree contains value */
let contains: Tree -> int -> bool =
    fn tree:Tree, target:int => {
        match search(tree)(target) {
            #empty(_) -> false
        |   #node(_) -> true
        }
    };

/* Count nodes in tree */
let count: Tree -> int =
    fn tree:Tree => {
        match tree {
            #empty(_) -> 0
        |   #node(n) ->
                1 + count(n.#left) + count(n.#right)
        }
    };

/* Calculate tree height */
let height: Tree -> int =
    fn tree:Tree => {
        match tree {
            #empty(_) -> 0
        |   #node(n) ->
                let left_height = height(n.#left);
                let right_height = height(n.#right);
                if (left_height > right_height) {
                    left_height + 1
                } else {
                    right_height + 1
                }
        }
    };

/* In-order traversal helper */
let print_inorder: Tree -> () =
    fn tree:Tree => {
        match tree {
            #empty(_) -> ()
        |   #node(n) ->
                print_inorder(n.#left);
                print(n.#value);
                print_inorder(n.#right)
        }
    };

/* Build a sample tree step by step */
let empty_tree:Tree = #empty(());
let tree1 = insert(empty_tree)(5);
let tree2 = insert(tree1)(3);
let tree3 = insert(tree2)(7);
let tree4 = insert(tree3)(1);
let tree5 = insert(tree4)(9);
let final_tree = insert(tree5)(4);

/* Test the operations */
println(count(final_tree));
println(height(final_tree));
println(contains(final_tree)(7));
println(contains(final_tree)(10));
print_inorder(final_tree);;