public class ASTNeg implements ASTNode {

    ASTNode exp;

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
	IValue v0 = exp.eval(e); 
	if (v0 instanceof VInt) { 
	    return new VInt(-((VInt)v0).getval()); 
	} else { 
	    throw new InterpreterError("illegal types to neg operator"); 
	}
    }
        
    public ASTNeg(ASTNode e)
    {
	exp = e;
    }

	public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError { // Γ ⊢ -M :
		ASTType t = exp.typeCheck(e);
		if (!(t instanceof ASTTInt)) { // Γ ⊢ M : int
	    	throw new TypeCheckError("Negation operator requires an integer type, found: " + t);
		}
		return new ASTTInt(); // Γ ⊢ -M : int
	}
}

