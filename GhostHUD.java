
public class GhostHUD {
    public void updateDisplay(RaceData data, ECUSettings ecu) {
        System.out.println("\n=== GHOST HUD ===");
        System.out.printf("Lap Time: %.3fs | Speed: %.1f km/h | Throttle: %.0f%% | Brake: %.0f%%%n",
                data.getLapTime(), data.getSpeed(), data.getThrottle() * 100, data.getBrakeForce() * 100);
        System.out.printf("Tire Temp: %.1f°C | Corner Speed: %.1f km/h%n", data.getTireTemp(), data.getCornerSpeed());
        System.out.printf("Suspension Load: %.2f | Gap Behind: %.2fs | Gap Ahead: %.2fs%n",
                data.getSuspensionLoad(), data.getGapToNextDriver(), data.getGapToLeadDriver());
        System.out.println("Mode: " + (ecu.isOverrideOn() ? "FULL COMMIT" : "SAFE"));
    }
}
