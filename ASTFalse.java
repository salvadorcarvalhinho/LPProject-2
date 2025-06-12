class ASTFalse implements ASTNode  {

    ASTFalse() {
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError{
	    return new VBool(false);
    }

}
