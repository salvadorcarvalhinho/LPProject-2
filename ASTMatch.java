public class ASTMatch implements ASTNode {
    ASTNode expr;
    ASTNode nil;
    String headId, tailId;
    ASTNode cons;

    public ASTMatch(ASTNode expr, ASTNode nil, ASTNode cons , String headId, String tailId) {
        this.expr = expr;
        this.nil = nil;
        this.headId = headId;
        this.tailId = tailId;
        this.cons = cons;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue v = expr.eval(e);

        if (v instanceof VNil) {
            return nil.eval(e);
        }

        if (v instanceof VCons || v instanceof VLCons) {
            VCons val = null;
            if (v instanceof VCons) {
                val =  (VCons)v;
            }
            else if (v instanceof VLCons) {
                val = ((VLCons) v).lazyeval();
            }
            VCons vcons = (VCons) val; 
            Environment<IValue> env2 = e.beginScope();
            env2.assoc(headId, vcons.getHead());
            env2.assoc(tailId, vcons.getTail());
            IValue res = cons.eval(env2);

            env2.endScope();
            return res;
        }
        throw new InterpreterError("Match can only be applied to list values");
    }
}