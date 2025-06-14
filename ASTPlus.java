public class ASTPlus implements ASTNode {

    ASTNode lhs, rhs;

        public IValue eval(Environment<IValue> e) throws InterpreterError {
                IValue v1 = lhs.eval(e);
                if (v1 instanceof VInt) {
                        IValue v2 = rhs.eval(e);
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

                return new ASTTInt(); // Γ ⊢ M + N : int
	}

}
