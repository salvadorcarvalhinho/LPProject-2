public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType type) {
        this.type = type;
    }
    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTRef) {
            ASTTRef otherRef = (ASTTRef) other;
            return this.type.isSubtypeOf(otherRef.getType(), env) && otherRef.getType().isSubtypeOf(this.type, env);
        }
        return false;
    }
    
    public ASTType getType() {
        return type;
    }
    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

}