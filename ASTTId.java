import java.util.Set;

public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    public String toStr() {
        return id;
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        if (simplified.contains(id)) {
            throw new InterpreterError("Type " + id + " already was simplified.");
        }
        simplified.add(id);

        ASTType type = null;
        try {
            type = env.find(((ASTTId) this).id);
        } catch (InterpreterError e) {
            throw new InterpreterError("Type " + id + " does not exist in environment.");
        }

        try {
            type = type.simplify(env, simplified);
            return type;
        } catch (InterpreterError ie) {
            return this;
        }
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other.toStr().equals(id)) {
            return true;
        }
        try {
            ASTType type = env.find(id);
            return type.isSubtypeOf(other, env);
        } catch (InterpreterError e) {
            return false;
        }
    }
}
