class ASTTrue implements ASTNode  {

    ASTTrue() {
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError{
	    return new VBool(true);
    }

}
