public class ASTBox implements ASTNode {
    private ASTNode node;

    public ASTBox(ASTNode node) {
        this.node = node;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue value = node.eval(env);
        if (!(value instanceof VInt) && !(value instanceof VBool) && 
        !(value instanceof VFunction) && !(value instanceof VCons) &&
        !(value instanceof VLCons)) {
            throw new InterpreterError("Boxing only supports integers, booleans, and functions");
        }
        return new VBox(value);
    }
}