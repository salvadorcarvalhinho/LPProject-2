public class ASTPlus implements ASTNode {

    ASTNode lhs, rhs;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = lhs.eval(e);
                IValue v2 = rhs.eval(e);
                if (v1 instanceof VString || v2 instanceof VString) {
                        return new VString(v1.toStr() + v2.toStr());
                }
                if (v1 instanceof VInt) {
                        if(v2 instanceof VInt) {
                                int i1 = ((VInt) v1).getval();
                                int i2 = ((VInt) v2).getval();
                                return new VInt(i1 + i2); 
                        }
                        
                } 
                throw new InterpreterError("illegal types to + operator");
        }

        public ASTPlus(ASTNode l, ASTNode r) {
                lhs = l;
                rhs = r;
        }

        public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ M + N :
	        ASTType t1 = lhs.typeCheck(env);
                if (!(t1 instanceof ASTTInt)) { // Γ ⊢ M : int
                        throw new TypeCheckError("Left operand of plus expression must be of type int, found: " + t1);
                }
                
                ASTType t2 = rhs.typeCheck(env);
                if (!(t2 instanceof ASTTInt)) { // Γ ⊢ N : int
                        throw new TypeCheckError("Right operand of plus expression must be of type int, found: " + t2);
                }
                
                if (t1 instanceof ASTTString || t2 instanceof ASTTString) {
                        return new ASTTString(); // Γ ⊢ M + N : string
                }
                return new ASTTInt(); // Γ ⊢ M + N : int
	}

}
