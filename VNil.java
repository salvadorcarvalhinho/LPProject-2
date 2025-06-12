public class VNil implements IValue {
    private static final VNil instance = new VNil();

    private VNil() {}

    public static VNil getval() {
        return instance;
    }

    @Override
    public String toStr() {
        return "nil";
    }
}

