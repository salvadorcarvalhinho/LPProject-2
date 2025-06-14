public class ASTWhile implements ASTNode {
    private ASTNode condition;
    private ASTNode body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        while (((VBool)condition.eval(env)).getval()) {
            body.eval(env);
        }
        return null;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType condType = condition.typeCheck(env);
        if (!(condType instanceof ASTTBool)) {
            throw new TypeCheckError("Condition of while loop must be of type bool, found: " + condType);
        }
        body.typeCheck(env);
        return new ASTTUnit();
    }
}
