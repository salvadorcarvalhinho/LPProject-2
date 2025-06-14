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

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType t = left.typeCheck(env);
        t = right.typeCheck(env);
        return t;
	}
}