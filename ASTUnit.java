public class ASTUnit implements ASTNode {
    public ASTUnit() {}

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VUnit();
    }

    public ASTType typeCheck(Environment<ASTType> env) {
        return new ASTTUnit();
    }
}