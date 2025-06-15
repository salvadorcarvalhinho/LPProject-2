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
}


