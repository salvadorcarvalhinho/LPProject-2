public class ASTMatch implements ASTNode {
    ASTNode expr;
    ASTNode nil;
    String headId, tailId;
    ASTNode cons;

    public ASTMatch(ASTNode expr, ASTNode nil, ASTNode cons , String headId, String tailId) {
        this.expr = expr;
        this.nil = nil;
        this.headId = headId;
        this.tailId = tailId;
        this.cons = cons;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = expr.eval(e);

        if (v instanceof VNil) {
            return nil.eval(e);
        }

        if (v instanceof VCons || v instanceof VLCons) {
            VCons val = null;
            if (v instanceof VCons) {
                val =  (VCons)v;
            }
            else if (v instanceof VLCons) {
                val = ((VLCons) v).lazyeval();
            }
            VCons vcons = (VCons) val; 
            Environment<IValue> env2 = e.beginScope();
            env2.assoc(headId, vcons.getHead());
            env2.assoc(tailId, vcons.getTail());
            IValue res = cons.eval(env2);

            env2.endScope();
            return res;
        }
        throw new InterpreterError("Match can only be applied to list values");
    }

    public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError { // match M { nil → N | x :: y → R } :
        ASTType list_A_type = expr.typeCheck(e); // Γ ⊢ M : list(A)
        if (!(list_A_type instanceof ASTTList)) {
            throw new TypeCheckError("Match can only be applied to list types");
        }
        ASTTList list_A = (ASTTList) list_A_type;

        ASTType nil_C = nil.typeCheck(e); // Γ ⊢ N : C
        
        Environment<ASTType> env = e.beginScope();
        try {
            env.assoc(headId, list_A.getElementType()); // Γ, x : A
            env.assoc(tailId, list_A); // Γ, y : list(A)
        } catch (InterpreterError ex) {
            throw new TypeCheckError("Type error in match: " + ex.getMessage());
        }
        ASTType cons_C = cons.typeCheck(env); // Γ, x : A, y : list(A) ⊢ R : C

        if (!nil_C.isSubtypeOf(cons_C, e)) {
            throw new TypeCheckError("Branches of match must have the same type, found: " + nil_C + " and " + cons_C);
        }

        return cons_C; // match M { nil → N | x :: y → R } : C
    }
}