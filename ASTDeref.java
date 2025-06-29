public class ASTDeref implements ASTNode {
    ASTNode node;

    ASTDeref(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError{
        IValue value = node.eval(e);
        if (value instanceof VBox) {
            IValue val = ((VBox) value).getval();

            return val;
        } else {
            throw new InterpreterError("Unboxing only works for boxed values");
        }
    }
    
    public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError {
        ASTType t = node.typeCheck(e);
        if (!(t instanceof ASTTRef)) { // Γ ⊢ M : ref(A)
            throw new TypeCheckError("Dereferencing can only be done on boxed types, found: " + t);
        }

        ASTType A = ((ASTTRef) t).getType();

        return A; // Γ ⊢ !M : A
    }

}
