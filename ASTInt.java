class ASTInt implements ASTNode  {
    int v;

    ASTInt(int v0) {
        v = v0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError
    {
	return new VInt(v);                
    }

    public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError {
        return new ASTTInt();
    }

}
