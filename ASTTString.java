public class ASTTString implements ASTType {

    public ASTTString() {}

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTString) {
            return true;
        }
        return false;
    }

    public String toStr() {
        return "string";
    }

}
