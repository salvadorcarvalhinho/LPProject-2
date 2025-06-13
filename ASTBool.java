class ASTBool implements ASTNode  {
    boolean bool;

    ASTBool(boolean b) {
        this.bool = b;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError{
	    return new VBool(bool);
    }

}
