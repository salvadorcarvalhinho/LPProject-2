public class ASTTStruct implements ASTType {

    private TypeBindList ll;

    public ASTTStruct(TypeBindList llp) {
        ll = llp;
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        return true;
    }
    
    public String toStr() {
        return "union { ... }";
    }

}