public class ASTTInt implements ASTType {
    
    public String toStr() {
        return "int";
    }
    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTInt) {
            return true;
        }
        return false;
    }
}


