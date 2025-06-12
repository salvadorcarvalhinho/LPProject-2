public class VCons implements IValue {
    private IValue valueHead;
    private IValue valueTail;

    public VCons(ASTNode head, ASTNode tail, Environment<IValue> env) {
        try {
            this.valueHead = head.eval(env);
            this.valueTail = tail.eval(env);
        } catch (InterpreterError e) {
            throw new RuntimeException("Error evaluating VCons: " + e.getMessage(), e);
        }
    }

    public IValue getHead() throws InterpreterError { 
        return valueHead;
    }

    public IValue getTail() throws InterpreterError {
        return valueTail;
    }

    public String toStr() {
        return valueHead.toStr() + "::" + valueTail.toStr();
    }
}