public class ASTIf implements ASTNode {
    private ASTNode condition;
    private ASTNode thenBranch;
    private ASTNode elseBranch;

    public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        boolean cond = ((VBool)condition.eval(env)).getval();
        if (cond) {
            return thenBranch.eval(env);
        } else {
            return elseBranch.eval(env);
        }
    }
}
