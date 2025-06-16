import java.util.*;

public class TypeBindList  {

        private HashMap<String,ASTType> lbl;

        public TypeBindList(HashMap<String,ASTType> ll) {
                lbl = ll;
        } 

        public ASTType get(String id) {
                return lbl.get(id);
        }

        public void set(String id, ASTType t) {
                lbl.put(id, t);
        }

        public List<String> getFields() {
                return new ArrayList<>(lbl.keySet());
        }

        public List<ASTType> getFieldTypes() {
                return new ArrayList<>(lbl.values());
        }

        public TypeBindList simplify(Environment<ASTType> env) {
                HashMap<String, ASTType> simplifiedMap = new HashMap<>();
                for (Map.Entry<String, ASTType> entry : lbl.entrySet()) {
                        String key = entry.getKey();
                        ASTType value = entry.getValue().simplify(env);
                        simplifiedMap.put(key, value);
                }
                return new TypeBindList(simplifiedMap);
        }
}