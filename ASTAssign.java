public class ASTAssign implements ASTNode {
    ASTNode node1;
    ASTNode node2;

    ASTAssign(ASTNode node1, ASTNode node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError
    {
        IValue value = node1.eval(e);
        IValue v = node2.eval(e);

        ((VBox)value).setval(v);
        return v;
    }
    
    public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError { // Γ ⊢ M := N :
        ASTType ref_A_type = node1.typeCheck(e);
        if (!(ref_A_type instanceof ASTTRef)) { // Γ ⊢ M : ref(A)
            throw new TypeCheckError("Left-hand side of assignment must be a reference type, found: " + ref_A_type);
        }
        ASTTRef ref_A = (ASTTRef) ref_A_type;
        
        
        ASTType A = node2.typeCheck(e); // Γ ⊢ N : A
        if (!A.isSubtypeOf(ref_A.getType(), e)) {
            throw new TypeCheckError("Right-hand side type " + A + " is not a subtype of reference type " + ref_A.getType());
        }

        return A; // Γ ⊢ M := N : A
    }
}
