public class Part {
    private String name;
    private double simBoost;
    private String category;

    public Part(String name, double simBoost, String category) {
        this.name = name;
        this.simBoost = simBoost;
        this.category = category;
    }

    public String getName() { return name; }
    public double getSimBoost() { return simBoost; }
    public String getCategory() { return category; }
}
