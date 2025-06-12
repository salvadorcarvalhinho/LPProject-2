public class ASTSeq implements ASTNode {
    private ASTNode left;
    private ASTNode right;
    
    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }
    
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        left.eval(env);
        return right.eval(env);
    }
}