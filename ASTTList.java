public class ASTTList implements ASTType {
    private ASTType elt;

    public ASTTList(ASTType eltt)
    {
        elt = eltt;
    }

    public ASTType getElementType() {
        return elt;
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTList) {
            ASTTList otherList = (ASTTList) other;
            return this.elt.isSubtypeOf(otherList.elt, env);
        }
        return false;
    }
    
    public String toStr() {
        return "list<"+elt.toStr()+">";
    }
    
}
