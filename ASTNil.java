public class ASTNil implements ASTNode {

    public IValue eval(Environment<IValue> e) {
        return VNil.getval();
    }
    
    public ASTType typeCheck(Environment<ASTType> e) {
        return new ASTTList(new ASTTUnit());
    }
}
