public class ASTComp implements ASTNode {
    private ASTNode term1;
    private String op;
    private ASTNode term2;

    public ASTComp(ASTNode term1, String op, ASTNode term2) {
        this.term1 = term1;
        this.op = op;
        this.term2 = term2;
    }

    public VBool eval(Environment<IValue> env) throws InterpreterError {
        int val1 = ((VInt)term1.eval(env)).getval();
        int val2 = ((VInt)term2.eval(env)).getval();
        boolean res = false;
        switch (op) {
            case "==" :
                res = (val1 == val2);
                break;
            case ">" :
                res = (val1 > val2);
                break;
            case "<" :
                res = (val1 < val2);
                break;
            case ">=" :
                res = (val1 >= val2);
                break;
            case "<=" :
                res = (val1 <= val2);
                break;
            case "~=" :
                res = (val1 != val2);
                break;
        
            default:
                res = false;
        }
        return new VBool(res);
    }
    
    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ M op N :
        ASTType t1 = term1.typeCheck(env);
        if (!(t1 instanceof ASTTInt)) { // Γ ⊢ M : int
            throw new TypeCheckError("Left operand of comparisson expression must be of type int, found: " + t1);
        }

        ASTType t2 = term2.typeCheck(env);
        if (!(t2 instanceof ASTTInt)) { // Γ ⊢ N : int
            throw new TypeCheckError("Right operand of comparisson expression must be of type int, found: " + t2);
        }
        
        return new ASTTBool(); // Γ ⊢ M op N : bool
    }
}