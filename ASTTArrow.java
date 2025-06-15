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
        }
        return false;
    }

    public String toStr() {
        return arg.toStr()+"->"+ret.toStr();
    }

    public ASTType simplify(Environment<ASTType> env) {
        ASTType simplifiedArg = arg.simplify(env);
        ASTType simplifiedRet = ret.simplify(env);
        return new ASTTArrow(simplifiedArg, simplifiedRet);
    }


}

