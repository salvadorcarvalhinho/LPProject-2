public class VUnion implements IValue {
    private String name;
    private IValue value;

    public VUnion(String name, IValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public IValue getValue() {
        return value;
    }

    @Override
    public String toStr() {
        return "VUnion{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
    
}
