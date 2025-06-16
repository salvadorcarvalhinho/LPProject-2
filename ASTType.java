import java.util.Set;

public interface ASTType  {
    String toStr();
    boolean isSubtypeOf(ASTType other, Environment<ASTType> env);
    ASTType simplify(Environment<ASTType> env, Set<String> simplified) throws InterpreterError;
}


