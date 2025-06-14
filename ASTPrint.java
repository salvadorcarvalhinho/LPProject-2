public class ASTPrint implements ASTNode {
    ASTNode exp;
    
    public ASTPrint(ASTNode e) {
        exp = e;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = exp.eval(e);
        System.out.print(v.toStr());
        return v;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType t = exp.typeCheck(env);
        return t;
	}
}