import java.util.List;
import java.util.ArrayList;

public class VFunction implements IValue {
    private List<String> parameters;
    private ASTNode body;
    private Environment<IValue> closureEnv;

    public VFunction(List<String> parameters, ASTNode body, Environment<IValue> env) {
        this.parameters = parameters;
        this.body = body;
        this.closureEnv = env;
    }

    public IValue apply(List<IValue> args) throws InterpreterError {
        if (args.size() > parameters.size()) {
            throw new InterpreterError("Function expected " + parameters.size() + 
                                      " arguments but got " + args.size());
        }
        
        Environment<IValue> callEnv = closureEnv.beginScope();
        
        for (int i = 0; i < args.size(); i++) {
            callEnv.assoc(parameters.get(i), args.get(i));
        }
        
        if (args.size() < parameters.size()) {
            List<String> remainingParams = new ArrayList<>();
            for (int i = args.size(); i < parameters.size(); i++) {
                remainingParams.add(parameters.get(i));
            }
            return new VFunction(remainingParams, body, callEnv);
        }
        
        return body.eval(callEnv);
    }

    @Override
    public String toStr() {
        return "VFun{" +
                "parameters=" + parameters +
                ", body=" + body +
                ", env=" + closureEnv +
                '}';
    }
}