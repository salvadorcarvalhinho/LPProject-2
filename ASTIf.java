public class ASTIf implements ASTNode {
    private ASTNode condition;
    private ASTNode thenBranch;
    private ASTNode elseBranch;

    public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        boolean cond = ((VBool)condition.eval(env)).getval();
        if (cond) {
            return thenBranch.eval(env);
        } else {
            return elseBranch.eval(env);
        }
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ (M, N, R) :
        ASTType condType = condition.typeCheck(env); // Γ ⊢ M : bool
        if (!(condType instanceof ASTTBool)) {
            throw new TypeCheckError("Condition must be of type Bool, found: " + condType);
        }

        ASTType then_A = thenBranch.typeCheck(env); // Γ ⊢ N : A
        ASTType else_A = elseBranch.typeCheck(env); // Γ ⊢ R : A 

        if (!then_A.isSubtypeOf(else_A)) {
            throw new TypeCheckError("Branches of if must have the same type, found: " + then_A + " and " + else_A);
        }

        return then_A; // Γ ⊢ (M, N, R) : A
    }
}
