class ASTTBool implements ASTType {
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean isSubtypeOf(ASTType other, Environment<ASTType> env) {
        if (other instanceof ASTTBool) {
            return true;
        }
        return false;
    }

    public ASTType simplify(Environment<ASTType> env) {
        return this;
    }
}