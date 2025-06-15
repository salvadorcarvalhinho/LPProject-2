public class VString implements IValue {
    private final String value;

    public VString(String value) {
        this.value = value;
    }

    public String getval() {
        return value;
    }

    public String toStr() {
        return value;
    }
}