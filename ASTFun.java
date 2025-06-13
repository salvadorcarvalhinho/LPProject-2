import java.util.List;

public class ASTFun implements ASTNode {
    private String arg;
    private ASTNode body;

    public ASTFun(String arg, ASTNode body) {
        this.arg = arg;
        this.body = body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VFunction(arg, body, env);
    }
}