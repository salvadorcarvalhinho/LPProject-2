import java.util.HashMap;
import java.util.Map;


public class ASTUnion implements ASTNode {
    private String name;
    private ASTNode value;

    public ASTUnion(String name, ASTNode value) {
        this.name = name;
        this.value = value;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = value.eval(env);
        return new VUnion(name, v);
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType t = value.typeCheck(env);
        return new ASTTUnion(new TypeBindList(new HashMap<String, ASTType>(Map.of(name, t))));
    }
}
