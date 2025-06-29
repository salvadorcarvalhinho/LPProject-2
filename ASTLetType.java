import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class ASTLetType implements ASTNode {
    private List<Bind> decls;
    private List<Bind> types;
    private ASTNode body;

    public ASTLetType(List<Bind> decls, List<Bind> types, ASTNode body) {
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
        for (Bind b : types) {
            String name = b.getId();
            try {
                en.assoc(name, new ASTTId(name));
            } catch (InterpreterError e) {
                throw new TypeCheckError("Duplicate identifier: " + name);
            }
        }

        for (Bind b : types) {
            String name = b.getId();
            ASTType type = b.getType();
            try {
                type = type.simplify(en, new HashSet<String>(Collections.singleton(name)));
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
                try {
                    type = type.simplify(en, new HashSet<>());
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