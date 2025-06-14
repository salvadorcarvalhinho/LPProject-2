class ASTTUnit implements ASTType {
        
    public ASTTUnit() {
    }
    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTUnit) {
            return true;
        }
        return false;
    }
    public String toStr() {
        return "()";
    }
}