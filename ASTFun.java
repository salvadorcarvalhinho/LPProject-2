public class ASTFun implements ASTNode {
    private String arg;
    private ASTType arg_type;
    private ASTNode body;

    public ASTFun(String arg, ASTType arg_type, ASTNode body) {
        this.arg = arg;
        this.arg_type = arg_type;
        this.body = body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VFunction(arg, body, env);
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ λx:A.M : A → B
        Environment<ASTType> newEnv = env.beginScope();
        try {
            newEnv.assoc(arg, arg_type); // Γ, x:A
        } catch (InterpreterError e) {
            throw new TypeCheckError("Error associating argument type: " + e.getMessage());
        }

        ASTType bodyType = body.typeCheck(newEnv); // Γ, x:A ⊢ M : B
        
        return new ASTTArrow(arg_type, bodyType);
    }
}