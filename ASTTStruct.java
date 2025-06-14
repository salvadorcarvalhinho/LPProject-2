public class ASTTStruct implements ASTType {

    private TypeBindList ll;

    public ASTTStruct(TypeBindList llp) {
        ll = llp;
    }

    public boolean isSubtypeOf(ASTType other) {
        return true;
    }
    
    public String toStr() {
        return "union { ... }";
    }

}