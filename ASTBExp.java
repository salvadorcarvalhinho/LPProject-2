public class ASTBExp implements ASTNode {
    private ASTNode term1;
    private String op;
    private ASTNode term2;

    public ASTBExp(ASTNode term1, String op, ASTNode term2) {
        this.term1 = term1;
        this.op = op;
        this.term2 = term2;
    }

    public VBool eval(Environment<IValue> env) throws InterpreterError {
        boolean val1 = ((VBool)term1.eval(env)).getval();
        boolean val2 = ((VBool)term2.eval(env)).getval();
        boolean res = false;
        switch (op) {
            case "&&" :
                res =  (val1 && val2);
                break;
            case "||" :
                res =  (val1 || val2);
                break;
        
            default:
                res = false;
        }
        return new VBool(res);
    }
    
}