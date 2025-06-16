import java.util.HashSet;
import java.util.Set;

public class ASTTArrow implements ASTType {
    ASTType arg;
    ASTType ret;

    public ASTTArrow(ASTType arg, ASTType ret) {
        this.arg = arg;
        this.ret = ret;
    }

    public ASTType getArgType() {
        return arg;
    }

    public ASTType getRetType() {
        return ret;
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTArrow) {
            ASTTArrow otherArrow = (ASTTArrow) other;
            return otherArrow.getArgType().isSubtypeOf(arg, env) && ret.isSubtypeOf(otherArrow.getRetType(), env);
        } else if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            try {
                other = env.find(otherId.toStr());
            } catch (InterpreterError ex) {
                return false;
            }
            return this.isSubtypeOf(other, env);
        }
        return false;
    }

    public String toStr() {
        return arg.toStr()+"->"+ret.toStr();
    }

    public ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError {
        ASTType simplifiedArg = arg.simplify(env, new HashSet<String>(simplified));
        ASTType simplifiedRet = ret.simplify(env, simplified);
        return new ASTTArrow(simplifiedArg, simplifiedRet);
    }


}

