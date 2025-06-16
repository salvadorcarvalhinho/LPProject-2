public class ASTTStruct implements ASTType {

    private TypeBindList ll;

    public ASTTStruct(TypeBindList llp) {
        ll = llp;
    }

    public ASTType getFieldType(String field) {
        return ll.get(field);
    }

    public TypeBindList getTypeBindList() {
        return ll;
    }
        
    public boolean isSubtypeOf(ASTType other, Environment<ASTType> e) {
        if (other instanceof ASTTStruct) {
            ASTTStruct otherStruct = (ASTTStruct) other;
            TypeBindList otherFields = otherStruct.getTypeBindList();
            TypeBindList thisFields = this.getTypeBindList();

            for (String field : otherFields.getFields()) {
                ASTType thisFieldType = thisFields.get(field);
                if (thisFieldType == null) {
                    return false;
                }
            }

            for (String field : otherFields.getFields()) {
                ASTType thisFieldType = thisFields.get(field);
                ASTType otherFieldType = otherFields.get(field);
                if (!thisFieldType.isSubtypeOf(otherFieldType, e)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
    
    public String toStr() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("struct { ");
        for (String field : ll.getFields()) {
            strBuilder.append(field).append(" : ").append(ll.get(field).toStr()).append("; ");
        }
        strBuilder.append("}");
        return strBuilder.toString();
    }

    public ASTType simplify(Environment<ASTType> env) {
        TypeBindList simplifiedFields = ll.simplify(env);
        return new ASTTStruct(simplifiedFields);
    }

}