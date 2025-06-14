
public interface ASTNode {

    IValue eval(Environment<IValue> e) throws InterpreterError;

    ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError;
	
}

