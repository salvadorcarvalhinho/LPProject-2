import java.util.*;

public class ASTTypeDef implements ASTNode {
HashMap<String,ASTType> ltd;
ASTNode body;

    public ASTTypeDef(HashMap<String,ASTType>  ltdp, ASTNode b) {
	ltd = ltdp;
    body = b;
    }
    
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return body.eval(env);
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        for (Map.Entry<String, ASTType> entry : ltd.entrySet()) {
            String name = entry.getKey();
            ASTType type = entry.getValue();
            try {
            ASTType existingType = env.find(name);
            if (existingType != null) {
                if (!existingType.equals(type)) {
                    throw new TypeCheckError("Type " + name + " is already defined with a different type.");
                }
            }
            env.assoc(name, type);
            } catch (InterpreterError e) {
                throw new TypeCheckError("Error while checking type " + name + ": " + e.getMessage());
            }
        }

        return body.typeCheck(env);
    }
}
