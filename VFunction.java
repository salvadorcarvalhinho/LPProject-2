import java.util.List;
import java.util.ArrayList;

public class VFunction implements IValue {
    private String arg;
    private ASTNode body;
    private Environment<IValue> closureEnv;

    public VFunction(String arg, ASTNode body, Environment<IValue> env) {
        this.arg = arg;
        this.body = body;
        this.closureEnv = env;
    }

    public IValue apply(IValue val) throws InterpreterError {

        Environment<IValue> callEnv = closureEnv.beginScope();
        callEnv.assoc(arg, val);
        
        return body.eval(callEnv);
    }

    @Override
    public String toStr() {
        return "VFun{" +
                "arg=" + arg +
                ", body=" + body +
                ", env=" + closureEnv +
                '}';
    }
}