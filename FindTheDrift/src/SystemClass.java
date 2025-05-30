import java.util.*;

// --- TIRE CLASS ---
class Tire {
    private String type;
    private double gripModifier;
    private double durability;
    private double wearRate;

    public Tire(String type, double gripModifier, double durability, double wearRate) {
        this.type = type;
        this.gripModifier = gripModifier;
        this.durability = durability;
        this.wearRate = wearRate;
    }

    public double getGripModifier() {
        return gripModifier;
    }

    public double getRemainingDurability(int laps) {
        return durability - (wearRate * laps);
    }

    public String getType() {
        return type;
    }
}

class Part {
    private String name;
    private String category;
    private double performanceBoost;

    public Part(String name, String category, double performanceBoost) {
        this.name = name;
        this.category = category;
        this.performanceBoost = performanceBoost;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPerformanceBoost() {
        return performanceBoost;
    }
}

class Car {
    private String name, type;
    private double weight, grip, power, intensityPercent, lapTime;
    private List<Part> parts = new ArrayList<>();
    private Tire tire;

    public Car(String name, String type, double weight, double grip, double power) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.grip = grip;
        this.power = power;
        this.intensityPercent = 0.0;
        this.lapTime = 0.0;
        this.tire = new Tire("Default", 1.0, 100.0, 5.0);
    }

    public void addPart(Part part) {
        parts.add(part);
        intensityPercent += part.getPerformanceBoost();
    }

    public void setTire(Tire tire) {
        this.tire = tire;
        this.grip *= tire.getGripModifier();
    }

    public double calculateGripToWeightRatio() {
        return grip / weight;
    }

    public void simulateLapTime(double trackBaseTime, double trackDifficulty) {
        double gripToWeight = calculateGripToWeightRatio();
        double powerEffect = power * 0.005;
        double gripEffect = gripToWeight * 8.0;
        double tireWearPenalty = (intensityPercent > 80) ? 2.0 : (intensityPercent > 60 ? 1.0 : 0.5);
        double intensityComp = (intensityPercent > 100) ? (intensityPercent - 100) * 0.1 : 0.0;
        lapTime = trackBaseTime - powerEffect - gripEffect + tireWearPenalty + intensityComp + trackDifficulty;
    }

    public void setRealLapTime(double realTime) {
        this.lapTime = realTime;
    }

    public double getLapTime() {
        return lapTime;
    }

    public String getName() {
        return name;
    }

    public double getGrip() {
        return grip;
    }

    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public void displayCarStats() {
        System.out.println("Car: " + name + " [" + type + "]");
        System.out.println("Weight: " + weight + "kg | Grip: " + grip + " | Power: " + power + "HP");
        System.out.println("Grip-to-Weight Ratio: " + calculateGripToWeightRatio());
        System.out.println("Intensity: " + intensityPercent + "%");
        System.out.println("Lap Time: " + lapTime + "s");
        System.out.println("Installed Parts:");
        for (Part part : parts) {
            System.out.println(" - " + part.getName() + " (" + part.getCategory() + ")");
        }
        System.out.println("Tires: " + tire.getType() + " | Remaining Durability: " + tire.getRemainingDurability(1));
    }
}

class MapSelector {
    private HashMap<String, Double> tracks = new HashMap<>();

    public MapSelector() {
        tracks.put("Suzuka Circuit", 75.0);
        tracks.put("Nurburgring", 90.0);
        tracks.put("Ebisu Drift Park", 65.0);
        tracks.put("Miami Drift GP", 70.0);
        tracks.put("Silverstone", 80.0);
    }

    public void displayTracks() {
        System.out.println("Available Tracks:");
        for (String name : tracks.keySet()) {
            System.out.println("- " + name);
        }
    }

    public double getBaseLapTime(String trackName) {
        return tracks.getOrDefault(trackName, 75.0);
    }
}

class Leaderboard {
    private List<String> entries = new ArrayList<>();

    public void addSimulatedResult(String driverName, Car car) {
        entries.add("SIM | " + driverName + " | " + car.getName() + " | " + car.getLapTime() + "s");
    }

    public void addRealResult(String driverName, Car car) {
        entries.add("REAL | " + driverName + " | " + car.getName() + " | " + car.getLapTime() + "s");
    }

    public void displayResults() {
        System.out.println("\n=== LEADERBOARD ===");
        for (String entry : entries) {
            System.out.println(entry);
        }
    }
}

class Garage {
    private List<Car> storedCars = new ArrayList<>();

    public void addCar(Car car) {
        storedCars.add(car);
        System.out.println(car.getName() + " added to your garage.");
    }

    public void listCars() {
        System.out.println("\nCars in Garage:");
        for (Car car : storedCars) {
            System.out.println("- " + car.getName());
        }
    }
}

class PartConnect {
    private List<Part> inventory = new ArrayList<>();

    public PartConnect() {
        inventory.add(new Part("Twin Turbo", "Engine", 15.0));
        inventory.add(new Part("Adjustable Coilovers", "Suspension", 10.0));
        inventory.add(new Part("Racing Transmission", "Transmission", 8.0));
        inventory.add(new Part("High-Flow Intake", "Intake", 6.0));
        inventory.add(new Part("Performance Exhaust", "Exhaust", 5.0));
        inventory.add(new Part("Weight Reduction Kit", "Chassis", 4.0));
        inventory.add(new Part("Race ECU", "ECU", 7.5));
    }

    public void displayInventory() {
        System.out.println("\nPartConnect Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Part part = inventory.get(i);
            System.out.println((i + 1) + ". " + part.getName() + " (" + part.getCategory() + ") - Boost: " + part.getPerformanceBoost() + "%");
        }
    }

    public Part getPart(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return null;
    }
}

class CornerZone {
    private String name;
    private double entrySpeedLimit;
    private double brakeZoneIntensity;
    private boolean requiresHandBrake;

    public CornerZone(String name, double entrySpeedLimit, double brakeZoneIntensity, boolean requiresHandBrake) {
        this.name = name;
        this.entrySpeedLimit = entrySpeedLimit;
        this.brakeZoneIntensity = brakeZoneIntensity;
        this.requiresHandBrake = requiresHandBrake;
    }

    public void analyze(Car car) {
        double gripToWeight = car.calculateGripToWeightRatio();
        double expectedCornerSpeed = gripToWeight * 500;

        System.out.println("\nCorner: " + name);
        System.out.println("Recommended Entry Speed: " + entrySpeedLimit + " km/h");
        System.out.println("Your Estimated Max Speed: " + expectedCornerSpeed + " km/h");

        if (expectedCornerSpeed >= entrySpeedLimit) {
            System.out.println("✅ Car can safely take this corner at or above recommended speed.");
        } else {
            System.out.println("⚠️ Risk of understeer or loss of control. Adjust tuning or braking.");
        }

        if (brakeZoneIntensity == 0) {
            System.out.println("Brake Zone: GREEN - Light braking.");
        } else if (brakeZoneIntensity == 1) {
            System.out.println("Brake Zone: YELLOW - Moderate braking.");
        } else {
            System.out.println("Brake Zone: RED - Heavy braking required.");
        }

        if (requiresHandBrake) {
            System.out.println("Handbrake Recommended: YES (Drift Technique)");
        } else {
            System.out.println("Handbrake Recommended: NO");
        }
    }
}

class CarCatalog {
    public Map<String, List<Car>> brandCategories = new LinkedHashMap<>();

    public CarCatalog() {
        // In a real app, this would pull from a database or API of real-world cars
        brandCategories.put("Toyota", Arrays.asList(
                new Car("Supra MK4", "Drift", 1500, 850, 450),
                new Car("GT86", "Drift", 1250, 800, 200)
        ));
        brandCategories.put("Nissan", Arrays.asList(
                new Car("240SX", "Drift", 1200, 780, 205),
                new Car("Skyline R34", "Street", 1550, 870, 500)
        ));
        brandCategories.put("Mazda", Arrays.asList(
                new Car("RX-7", "Drift", 1220, 810, 255),
                new Car("MX-5 Miata", "Lightweight", 1000, 760, 180)
        ));
        brandCategories.put("BMW", Arrays.asList(
                new Car("M3 E46", "Track", 1450, 850, 343),
                new Car("M4 G82", "Track", 1600, 900, 510)
        ));
    }

    public void displayBrands() {
        int i = 1;
        for (String brand : brandCategories.keySet()) {
            System.out.println(i++ + ". " + brand);
        }
    }

    public void displayCarsByBrand(String brand) {
        List<Car> cars = brandCategories.getOrDefault(brand, new ArrayList<>());
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.println((i + 1) + ". " + car.getName() + " [" + car.getType() + "]");
        }
    }

    public Car getCar(String brand, int index) {
        List<Car> cars = brandCategories.getOrDefault(brand, new ArrayList<>());
        if (index >= 0 && index < cars.size()) {
            return cars.get(index);
        }
        return null;
    }
}

// --- SYSTEM CLASS ---
public class SystemClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MapSelector map = new MapSelector();
        Leaderboard leaderboard = new Leaderboard();
        Garage garage = new Garage();
        PartConnect shop = new PartConnect();
        CarCatalog catalog = new CarCatalog();

        System.out.println("Choose mode: 1 = Simulated, 2 = Real-world Lap");
        int mode = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select a car brand:");
        catalog.displayBrands();
        int brandChoice = scanner.nextInt();
        scanner.nextLine();

        String selectedBrand = new ArrayList<>(catalog.brandCategories.keySet()).get(brandChoice - 1);
        System.out.println("Select a car from " + selectedBrand + ":");
        catalog.displayCarsByBrand(selectedBrand);
        int carChoice = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = catalog.getCar(selectedBrand, carChoice - 1);
        garage.addCar(selectedCar);

        map.displayTracks();
        System.out.print("\nEnter track name: ");
        String trackChoice = scanner.nextLine();
        double baseLapTime = map.getBaseLapTime(trackChoice);
        double difficulty = 3.0;

        boolean addMoreParts = true;
        while (addMoreParts) {
            shop.displayInventory();
            System.out.print("\nSelect part number to install on " + selectedCar.getName() + " (0 to stop): ");
            int partChoice = scanner.nextInt();
            scanner.nextLine();

            if (partChoice == 0) {
                addMoreParts = false;
            } else {
                Part selectedPart = shop.getPart(partChoice - 1);
                if (selectedPart != null) {
                    selectedCar.addPart(selectedPart);
                }
            }
        }

        if (mode == 2) {
            System.out.print("\nEnter the number of real-world parts to input: ");
            int realPartsCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < realPartsCount; i++) {
                System.out.print("Enter part name: ");
                String partName = scanner.nextLine();
                System.out.print("Enter category (e.g., Engine, Suspension): ");
                String partCategory = scanner.nextLine();
                System.out.print("Enter estimated performance boost (as %): ");
                double boost = scanner.nextDouble();
                scanner.nextLine();

                Part manualPart = new Part(partName, partCategory, boost);
                selectedCar.addPart(manualPart);
            }
        }

        Tire driftTires = new Tire("Drift", 1.2, 100, 7);
        selectedCar.setTire(driftTires);

        if (mode == 1) {
            selectedCar.simulateLapTime(baseLapTime, difficulty);
            leaderboard.addSimulatedResult("AI Driver", selectedCar);
        } else {
            System.out.print("\nEnter real-world lap time (in seconds): ");
            double realTime = scanner.nextDouble();
            selectedCar.setRealLapTime(realTime);
            leaderboard.addRealResult("Team Driver", selectedCar);
        }

        CornerZone corner1 = new CornerZone("Turn 1 Hairpin", 95.0, 2, true);
        corner1.analyze(selectedCar);

        selectedCar.displayCarStats();
        leaderboard.displayResults();
        garage.listCars();
    }
}


