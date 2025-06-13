import java.util.List;
import java.util.ArrayList;

public class ASTApp implements ASTNode {
    private ASTNode function;
    private ASTNode argument;
    
    public ASTApp(ASTNode function, ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }
    
    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue functionValue = function.eval(env);
        VFunction func = (VFunction) functionValue;
        
        IValue val = argument.eval(env);
        
        return func.apply(val);
    }
}