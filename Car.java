import java.util.*;

public class Car {
    private final String model, type;
    private double weight, baseGrip, crankHP, wheelHP, intensity, lastLap;
    private int laps;
    private final List<Part> parts = new ArrayList<>();
    private Tire tire = new Tire("Default", 1.0, 100.0, 5.0);

    // New for AI
    private double speed = 100;
    private boolean drifting = false;
    private boolean driftAssist = false;
    private int gear = 3;
    private final int maxGear = 6;

    // Dimensions
    private double width;  // meters
    private double length; // meters

    // For mistake/overtake logic
    private boolean madeMistake = false;

    // Constructors
    public Car(String model, String type, double weight, double grip, double crankHP) {
        this(model, type, weight, grip, crankHP, 1.85, 4.5);
    }
    public Car(String model, String type, double weight, double grip, double crankHP, double width, double length) {
        this.model = model;
        this.type = type;
        this.weight = weight;
        this.baseGrip = grip;
        this.crankHP = crankHP;
        this.wheelHP = crankHP * 0.85;
        this.intensity = 0.0;
        this.lastLap = 0.0;
        this.laps = 0;
        this.width = width;
        this.length = length;
    }

    // === GETTERS for Leaderboard and UI ===
    public String getModelName() { return model; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    public double getBaseGrip() { return baseGrip; }
    public double getCrankHP() { return crankHP; }
    public double getWheelHP() { return wheelHP; }
    public double getIntensity() { return intensity; }
    public double getLastLapTime() { return lastLap; }
    public int getLapsCompleted() { return laps; }
    public Tire getCurrentTire() { return tire; }
    public List<Part> getInstalledParts() { return parts; }
    public double getWidth() { return width; }
    public double getLength() { return length; }
    public boolean hasMadeMistake() { return madeMistake; }
    public void setMadeMistake(boolean val) { this.madeMistake = val; }

    // === PERFORMANCE LOGIC ===
    public double getEffectiveGrip() { return baseGrip * tire.getGripModifier(); }
    public double getGripToWeight() { return getEffectiveGrip() / weight; }

    public void installPart(Part p) { parts.add(p); intensity += p.getSimBoost(); }
    public void changeTire(Tire t) { tire = t; }

    public void simulateLap(double baseTime, double trackDiff) {
        double power = wheelHP * 0.005, grip = getGripToWeight() * 8.0;
        double tirePen = intensity > 100 ? 3 : intensity > 80 ? 2 : intensity > 60 ? 1 : intensity > 40 ? 0.8 : 0.5;
        double intPen = intensity > 100 ? (intensity - 100) * 0.1 : 0;
        double worn = tire.getRemainingDurability(laps) < 20 ? 1 : 0;
        // ... your sim logic here ...
    }

    // === NEW METHODS FOR AI/Prediction SUPPORT ===
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public boolean isDrifting() { return drifting; }
    public void setDrifting(boolean drifting) { this.drifting = drifting; }
    public void brakeEarly() { this.speed *= 0.85; }
    public void setDriftAssist(boolean enable) { this.driftAssist = enable; }
    public void accelerate() { this.speed *= 1.05; }
    public boolean canDrift() { return true; }
    public int getGear() { return gear; }
    public void setGear(int gear) { this.gear = Math.max(1, Math.min(gear, maxGear)); }
    public int getMaxGear() { return maxGear; }
}


