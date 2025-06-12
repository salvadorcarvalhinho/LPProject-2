public class VLCons implements IValue {
    private ASTNode head;
    private ASTNode tail;
    private Environment<IValue> env;

    public VLCons(ASTNode head, ASTNode tail, Environment<IValue> env) {
        this.head = head;
        this.tail = tail;
        this.env = env;
    }

    public ASTNode getHead() {
        return head;
    }

    public ASTNode getTail() {
        return tail;
    }

    public VCons lazyeval() throws InterpreterError {
        return new VCons(head, tail, env);
    }

    public String toStr() {
        return "[unevaluated] LazyCons";
    }
    
}
