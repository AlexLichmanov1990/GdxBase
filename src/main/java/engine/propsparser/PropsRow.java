package engine.propsparser;

public class PropsRow {
    private String row;
    private String name;
    private String value;
    private boolean built = false;

    public PropsRow(String row) {
        this.row = row;
    }

    public PropsRow build() {
        return build(" = ");
    }

    public PropsRow build(String splitRegex) {
        if (built) return this;
        String[] vals = row.split(splitRegex);
        if (vals.length < 2) throw new IllegalStateException("The row cannot be built.");
        name = vals[0].trim();
        value = vals[1].trim();
        this.built = true;
        return this;
    }

    public String getName() {
        if (!built) throw new IllegalStateException("The row hasn't been built.");
        return name;
    }

    public String getValue() {
        if (!built) throw new IllegalStateException("The row hasn't been built.");
        return value;
    }
}
