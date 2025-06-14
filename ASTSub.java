public class ASTSub implements ASTNode {

    ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	IValue v1 = lhs.eval(e);
	IValue v2 = rhs.eval(e);
	if (v1 instanceof VInt && v2 instanceof VInt) {
	    return new VInt(((VInt) v1).getval() - ((VInt) v2).getval());
	} else {
	    throw new InterpreterError("illegal types to + operator");
	}
    }

    public ASTSub(ASTNode l, ASTNode r) {
	lhs = l;
	rhs = r;
    }

	public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ M - N :
	    ASTType t1 = lhs.typeCheck(env);
        if (!(t1 instanceof ASTTInt)) { // Γ ⊢ M : int
            throw new TypeCheckError("Left operand of sub expression must be of type int, found: " + t1);
        }

        ASTType t2 = rhs.typeCheck(env);
        if (!(t2 instanceof ASTTInt)) { // Γ ⊢ N : int
            throw new TypeCheckError("Right operand of sub expression must be of type int, found: " + t2);
        }

        return new ASTTInt(); // Γ ⊢ M - N : int
	}
}
