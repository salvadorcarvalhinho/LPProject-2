import java.util.List;
import java.util.ArrayList;

public class ASTApp implements ASTNode {
    private ASTNode function;
    private ASTNode argument;
    
    public ASTApp(ASTNode function, ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }
    
    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue functionValue = function.eval(env);
        VFunction func = (VFunction) functionValue;
        
        IValue val = argument.eval(env);
        
        return func.apply(val);
    }

    @Override
    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError { // Γ ⊢ MN : 
        ASTType C = argument.typeCheck(env); // Γ ⊢ N:C

        ASTType funcType = function.typeCheck(env);
        if (!(funcType instanceof ASTTArrow)) { // Γ ⊢ M : A → B
            throw new TypeCheckError("Expected a function type, but found: " + funcType.toStr());
        }
        ASTTArrow A_arrow_B = (ASTTArrow) funcType;

        if (!C.isSubtypeOf(A_arrow_B.getArgType(), env)) { // C <: A
            throw new TypeCheckError("Argument type " + C.toStr() + " does not match function's expected argument type " + A_arrow_B.getArgType().toStr());
        }
        
        return A_arrow_B.getRetType(); // Γ ⊢ MN : B
    }
}