public class ASTTString implements ASTType {

    public ASTTString() {}

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTString) {
            return true;
        }
        return false;
    }

    public String toStr() {
        return "string";
    }

}
