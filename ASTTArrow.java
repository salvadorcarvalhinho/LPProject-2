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

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTArrow) {
            ASTTArrow otherArrow = (ASTTArrow) other;
            return otherArrow.getArgType().isSubtypeOf(arg) && ret.isSubtypeOf(otherArrow.getRetType());
        }
        return false;
    }

    public String toStr() {
        return arg.toStr()+"->"+ret.toStr();
    }   
}

