import java.util.HashMap;
import java.util.Map;

public class ASTStruct implements ASTNode {
    private HashMap<String, ASTNode> fields;

    public ASTStruct(HashMap<String, ASTNode> fields) {
        this.fields = fields;
    }

    public HashMap<String, ASTNode> getFields() {
        return fields;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        HashMap<String, IValue> evaluatedFields = new HashMap<>();
        for (Map.Entry<String, ASTNode> entry : fields.entrySet()) {
            evaluatedFields.put(entry.getKey(), entry.getValue().eval(env));
        }
        return new VStruct(evaluatedFields);
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        HashMap<String, ASTType> typeCheckedFields = new HashMap<>();
        for (Map.Entry<String, ASTNode> entry : fields.entrySet()) {
            ASTType fieldType = entry.getValue().typeCheck(env);
            if (fieldType == null) {
                throw new TypeCheckError("Field " + entry.getKey() + " has no type.");
            }
            typeCheckedFields.put(entry.getKey(), fieldType);
        }
        return new ASTTStruct(new TypeBindList(typeCheckedFields));
    }
}