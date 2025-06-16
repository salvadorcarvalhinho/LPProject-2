import java.util.Set;

public class ASTTInt implements ASTType {
    
    public String toStr() {
        return "int";
    }
    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTInt) {
            return true;
        }
        return false;
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        return this;
    }
}


