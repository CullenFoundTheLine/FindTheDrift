import java.util.*;

public class FindTheDriftLeaderboard {
    public static void main(String[] args) {
        // Driver names
        String[] drivers = {"Aki", "Blitz", "Ghost", "Nova", "Draco"};

        // Lap times for each driver
        double[] lapTimes = new double[drivers.length];

        // Generate random lap times (between 60.0s and 90.0s)
        Random rand = new Random();
        for (int i = 0; i < lapTimes.length; i++) {
            lapTimes[i] = 60.0 + rand.nextDouble() * 600.0;
        }

        // Combine names and times into a list of entries
        List<DriverEntry> leaderboard = new ArrayList<>();
        for (int i = 0; i < drivers.length; i++) {
            leaderboard.add(new DriverEntry(drivers[i], lapTimes[i]));
        }

        // Sort by lap time (ascending)
        leaderboard.sort(Comparator.comparingDouble(entry -> entry.lapTime));

        // Print leaderboard
        System.out.println("🏆 FindTheDrift - Leaderboard 🏆");
        int rank = 1;
        for (DriverEntry entry : leaderboard) {
            System.out.printf("%d. %s - %.2f seconds%n", rank, entry.name, entry.lapTime);
            rank++;
        }
    }

    // Helper class to store driver info
    static class DriverEntry {
        String name;
        double lapTime;

        DriverEntry(String name, double lapTime) {
            this.name = name;
            this.lapTime = lapTime;
        }
    }
}

