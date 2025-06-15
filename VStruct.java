import java.util.HashMap;
import java.util.Map;

public class VStruct implements IValue {
    private HashMap<String, IValue> fields;

    public VStruct(HashMap<String, IValue> fields) {
        this.fields = fields;
    }

    public IValue get(String key) {
        return fields.get(key);
    }

    public String toStr() {
        StringBuilder strBuild = new StringBuilder();
        boolean first = true;
        strBuild.append("{ ");
        for (Map.Entry<String, IValue> entry : fields.entrySet()) {
            if (!first) strBuild.append(", ");
            strBuild.append(entry.getKey()).append(" = ").append(entry.getValue().toStr());
            first = false;
        }
        strBuild.append(" }");
        return strBuild.toString();
    }
    
}