public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    public String toStr() {
        return id;
    }
    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTId) {
            ASTTId otherId = (ASTTId) other;
            return this.id.equals(otherId.id);
        }
        return false;
    }
}
