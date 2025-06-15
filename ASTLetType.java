import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ASTLetType implements ASTNode {
    private List<Bind> decls;
    private HashMap<String, ASTType> types;
    private ASTNode body;

    public ASTLetType(List<Bind> decls, HashMap<String, ASTType> types, ASTNode body) {
        this.decls = decls;
        this.types = types;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        Environment<IValue> en = env.beginScope();
        for (Bind p : decls) {
            String id = p.getId();
            ASTNode exp = p.getExp();
            en.assoc(id, exp.eval(en));
        }
        IValue v = body.eval(en);
        en.endScope();
        return v;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        Environment<ASTType> en = env.beginScope();
        for (Map.Entry<String, ASTType> entry : types.entrySet()) {
            String name = entry.getKey();
            ASTType type = entry.getValue();
            type = type.simplify(en);
            try {
                en.assoc(name, type);
            } catch (InterpreterError e) {
                throw new TypeCheckError("Duplicate identifier: " + name);
            }
        }

        for (Bind b : decls) {
            String id = b.getId();
            ASTNode exp = b.getExp();
            ASTType type = b.getType();
            if (type != null) {
                type = type.simplify(en);
                try {
                    en.assoc(id, type); // Γ, id : B
                } catch (InterpreterError e) {
                    throw new TypeCheckError("Duplicate identifier: " + id);
                }
            }
            
            ASTType B = exp.typeCheck(en); // Γ ⊢ N : B
            if (type == null) {
                try {
                    en.assoc(id, B); // Γ, id : B
                } catch (InterpreterError e) {
                    throw new TypeCheckError("Duplicate identifier: " + id);
                }
            } else {
                if (!B.isSubtypeOf(type, en)) {
                    throw new TypeCheckError("Type mismatch for identifier: " + id + ". Expected: " + type.toStr() + ", Found: " + B.toStr());
                }
            }
        }

        ASTType A = body.typeCheck(en); // Γ, id : B ⊢ M : A
        en.endScope();

        return A;
    }
}