import java.util.List;

public class ASTFun implements ASTNode {
    private List<String> parameters;
    private ASTNode body;

    public ASTFun(List<String> parameters, ASTNode body) {
        this.parameters = parameters;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VFunction(parameters, body, env);
    }
}