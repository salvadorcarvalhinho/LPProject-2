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

        if (value instanceof VBox) {
            IValue v = node2.eval(e);

            if (!(v instanceof VInt) && !(v instanceof VBool) && 
            !(v instanceof VFunction) && !(v instanceof VBox) &&
            !(v instanceof VCons) && !(v instanceof VLCons)) {
                throw new InterpreterError("Attempting to assign an invalid value ");
            }
            ((VBox)value).setval(v);
            return v;
        } else {
            throw new InterpreterError("Attempting to assign to a non-box value");
        }
    }
    
}
