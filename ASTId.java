public	class ASTId implements ASTNode	{
    String id;	
    
    public ASTId(String id)	{
        this.id = id;
    }

    public IValue eval(Environment<IValue> env)	throws
    InterpreterError {
    return env.find(id);	
    }

    public ASTType typeCheck(Environment<ASTType> env)	throws TypeCheckError { // Γ ⊢ x :
        try {
            ASTType A = env.find(id); // Γ(x) = A
            return A; // Γ ⊢ x : A
        } catch (InterpreterError e) {
            throw new TypeCheckError("Identifier not found: " + id);
        }
    }

}	
