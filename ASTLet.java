import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    Environment<IValue> en = e.beginScope();
        for (Bind dec : decls) {
            en.assoc(dec.getId(), dec.getExp().eval(en));
        }
        IValue v = body.eval(en);
        en.endScope();
        return v;
    }

    public ASTLet(List<Bind> decls, ASTNode b) {
	    this.decls = decls;
	    body = b;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ let x = N in M :
        Environment<ASTType> en = env.beginScope();
        for (Bind dec : decls) {
            ASTType B = dec.getExp().typeCheck(en); // Γ ⊢ N : B
            try {
                en.assoc(dec.getId(), B); // Γ, id : B
            } catch (InterpreterError e) {
                throw new TypeCheckError("Duplicate identifier: " + dec.getId());
            }
        }

        ASTType A = body.typeCheck(en); // Γ, id : B ⊢ M : A
        en.endScope();

        return A; // Γ ⊢ let x = N in M : A
    }

}
