import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ASTTUnion implements ASTType {

    private TypeBindList ll;

    public ASTTUnion(TypeBindList llp) {
        ll = llp;
    }

    public TypeBindList getTypeBindList() {
        return ll;
    }

    public List<String> getFields() {
        return ll.getFields();
    }

    public List<ASTType> getFieldTypes() {
        return ll.getFieldTypes();
    }
    
    public String toStr() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("union { ");
        for (String field : ll.getFields()) {
            strBuilder.append(field).append(" : ").append(ll.get(field).toStr()).append("; ");
        }
        strBuilder.append("}");
        return strBuilder.toString();
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> e) {
        if (other instanceof ASTTUnion) {
            ASTTUnion otherUnion = (ASTTUnion) other;
            TypeBindList otherFields = otherUnion.ll;
            TypeBindList thisFields = this.ll;

            for (String field : thisFields.getFields()) {
                ASTType otherFieldType = otherFields.get(field);
                if (otherFieldType == null) {
                    return false;
                }
            }

            for (String field : thisFields.getFields()) {
                ASTType thisFieldType = thisFields.get(field);
                ASTType otherFieldType = otherFields.get(field);
                if (!otherFieldType.isSubtypeOf(thisFieldType, e)) {
                    return false;
                }
            }
            return true;
        } else if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            try {
                other = e.find(otherId.toStr());
            } catch (InterpreterError ex) {
                return false;
            }
            return this.isSubtypeOf(other, e);
        }

        return false;
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        for (String field : ll.getFields()) {
            ASTType fieldType = ll.get(field);
            ASTType simplifiedFieldType = fieldType.simplify(env, new HashSet<String>(simplified));
            ll.set(field, simplifiedFieldType);
        }

        return this;
    }

}
