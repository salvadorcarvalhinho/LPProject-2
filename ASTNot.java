public class ASTNot implements ASTNode{
    private ASTNode term;

    public ASTNot(ASTNode term) {
        this.term = term;
    }

    public VBool eval(Environment<IValue> env) throws InterpreterError {
        boolean val1 = ((VBool)term.eval(env)).getval();
        return new VBool(!val1);
    }
}
