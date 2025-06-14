public class ASTPrintln implements ASTNode {
    ASTNode exp;
    
    public ASTPrintln(ASTNode e) {
        exp = e;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = exp.eval(e);
        System.out.println(v.toStr());
        return v;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType t = exp.typeCheck(env);
        return t;
	}
}