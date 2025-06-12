public class ASTWhile implements ASTNode {
    private ASTNode condition;
    private ASTNode body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        while (((VBool)condition.eval(env)).getval()) {
            body.eval(env);
        }
        return null;
    }
}
