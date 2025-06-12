public class VBox implements IValue {
    private IValue value;

    public VBox(IValue value) {
        this.value = value;
    }

    public void setval(IValue value) {
        this.value = value;
    }

    public IValue getval() {
        return value;
    }

    @Override
    public String toStr() {
        return "Box{" +
                "value=" + value +
                '}';
    }
}
