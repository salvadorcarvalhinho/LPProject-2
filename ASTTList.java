import java.util.Set;

public class ASTTList implements ASTType {
    private ASTType elementType;

    public ASTTList(ASTType elementTypet)
    {
        elementType = elementTypet;
    }

    public ASTType getElementType() {
        return elementType;
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTList) {
            ASTTList otherList = (ASTTList) other;
            return this.elementType.isSubtypeOf(otherList.elementType, env);
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
    
    public String toStr() {
        return "list<"+elementType.toStr()+">";
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        ASTType simplifiedElementType = elementType.simplify(env, simplified);
        return new ASTTList(simplifiedElementType);
    }    
}
