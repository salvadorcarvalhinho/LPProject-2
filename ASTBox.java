public class ASTBox implements ASTNode {
    private ASTNode node;

    public ASTBox(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue value = node.eval(env);
        return new VBox(value);
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ box(M) :
        ASTType A = node.typeCheck(env); // Γ ⊢ M : A
        return new ASTTRef(A); // Γ ⊢ box(M) : ref(A)
    }
}