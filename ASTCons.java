public class ASTCons implements ASTNode {
    ASTNode head, tail;
    boolean lazyList;

    public ASTCons(ASTNode head, ASTNode tail, boolean lazyList) {
        this.head = head;
        this.tail = tail;
        this.lazyList = lazyList;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        if (lazyList) {
            return new VLCons(head,tail, e);
        }
        return new VCons(head, tail, e);
    }

    public ASTType typeCheck(Environment<ASTType> e) throws TypeCheckError { // Γ ⊢ M :: N :
        ASTType list_a_type = tail.typeCheck(e);
        if (!(list_a_type instanceof ASTTList)) { // Γ ⊢ N : list(A)
            throw new TypeCheckError("Tail of cons must be a list, found: " + list_a_type);
        }
        ASTTList list_a = (ASTTList) list_a_type;
        
        ASTType A = head.typeCheck(e); // Γ ⊢ M : A
        if (!A.equals(list_a.getElementType())) {
            throw new TypeCheckError("Head type " + A + " does not match tail element type " + list_a.getElementType());
        }
        
        return list_a; // Γ ⊢ M :: N : list(A)
    }
}