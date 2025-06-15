public class ASTFieldSelect implements ASTNode {
    private ASTNode record;
    private String field;

    public ASTFieldSelect(ASTNode record0, String field0) {
        record = record0;
        field = field0;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue recVal = record.eval(env);
        if (!(recVal instanceof VStruct)) {
            throw new InterpreterError("Field select on non-structure value");
        }
        VStruct vrec = (VStruct) recVal;
        IValue val = vrec.get(field);
        if (val == null) {
            throw new InterpreterError("Field '" + field + "' not found in structure");
        }
        return val;
    }

    public ASTType typeCheck(Environment<ASTType> env) throws TypeCheckError {
        ASTType recType = record.typeCheck(env);
        if (!(recType instanceof ASTTStruct)) {
            throw new TypeCheckError("Field select on non-structure type");
        }

        ASTType fieldType = ((ASTTStruct)recType).getFieldType(field);
        if (fieldType == null) {
            throw new TypeCheckError("Field '" + field + "' not found in structure type");
        }
        return fieldType;
    }
}