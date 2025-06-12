class VBool implements IValue {
    boolean b;

    VBool(boolean b) {
        this.b = b;
    }

    boolean getval() {
        return b;
    }

    public String toStr() {
        if (b) {
            return "true";
        }
        return "false";
    }
}