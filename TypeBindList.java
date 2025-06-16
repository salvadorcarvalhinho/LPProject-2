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
}