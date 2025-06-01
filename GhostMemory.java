public class GhostMemory {
    private int totalLaps = 0;
    private double avgCornerSpeed = 0;

    public void updateProfile(RaceData data) {
        totalLaps++;
        avgCornerSpeed = ((avgCornerSpeed * (totalLaps - 1)) + data.getCornerSpeed()) / totalLaps;
        System.out.printf("Driver Profile Updated: Avg Corner Speed: %.2f km/h (Total Laps: %d)%n", avgCornerSpeed, totalLaps);
    }
}
