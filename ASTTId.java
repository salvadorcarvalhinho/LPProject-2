public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    public String toStr() {
        return id;
    }

    public ASTType simplify(Environment<ASTType> env) {
        try {
            ASTType type = env.find(id);
            while (type instanceof ASTTId) {
                type = env.find(((ASTTId) type).toStr());
            }
            return type;
        } catch (InterpreterError e) {
            return this; // If not found, return itself
        }
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        try {
            ASTType type = env.find(id);
            return type.isSubtypeOf(other, env);
        } catch (InterpreterError e) {
            return false;
        }
    }
}
