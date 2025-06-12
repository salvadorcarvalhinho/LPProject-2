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
}