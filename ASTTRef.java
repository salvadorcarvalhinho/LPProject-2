import java.util.Set;

public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType type) {
        this.type = type;
    }
    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTRef) {
            ASTTRef otherRef = (ASTTRef) other;
            return this.type.isSubtypeOf(otherRef.getType(), env) && otherRef.getType().isSubtypeOf(this.type, env);
        } else if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            try {
                other = env.find(otherId.toStr());
            } catch (InterpreterError ex) {
                return false;
            }
            return this.isSubtypeOf(other, env);
        }
        return false;
    }
    
    public ASTType getType() {
        return type;
    }
    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        ASTType simplifiedType = type.simplify(env, simplified);
        return new ASTTRef(simplifiedType);
    }

}