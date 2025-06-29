import java.util.Set;

class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTUnit) {
            return true;
        }
        return false;
    }
    public String toStr() {
        return "()";
    }
    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        return this;
    }
}