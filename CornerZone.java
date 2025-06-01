public class CornerZone {
    private String cornerName;
    private double entryPoint; // 0.0-1.0 (start to end of track)
    private double exitPoint;  // 0.0-1.0
    private boolean driftOptimal;

    public CornerZone(String name, double entry, double exit, boolean driftOptimal) {
        this.cornerName = name;
        this.entryPoint = entry;
        this.exitPoint = exit;
        this.driftOptimal = driftOptimal;
    }

    public String getCornerName() { return cornerName; }
    public double getEntryPoint() { return entryPoint; }
    public double getExitPoint() { return exitPoint; }
    public boolean isDriftOptimal() { return driftOptimal; }
}

