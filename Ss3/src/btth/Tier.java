package btth;

public abstract class Tier {
    protected String name;

    public Tier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}