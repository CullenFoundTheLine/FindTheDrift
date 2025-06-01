public class Tire {
    private String name;
    private double gripModifier;
    private double durability;
    private double tempRange;

    public Tire(String name, double gripModifier, double durability, double tempRange) {
        this.name = name;
        this.gripModifier = gripModifier;
        this.durability = durability;
        this.tempRange = tempRange;
    }

    public String getName() { return name; }
    public double getGripModifier() { return gripModifier; }
    public double getDurability() { return durability; }
    public double getTempRange() { return tempRange; }

    public double getRemainingDurability(int laps) {
        return durability - (laps * (100.0 / durability));
    }
}


