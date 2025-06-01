import java.util.*;

public class FindTheDriftApp {

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        List<Double> carPositions = new ArrayList<>();
        List<TrackInfo> trackSegments = new ArrayList<>();

        // With width/length for realism
        cars.add(new Car("Supra MK4", "Drift", 1500, 1.2, 500, 1.81, 4.52));
        cars.add(new Car("RX7 FD", "Drift", 1250, 1.15, 420, 1.76, 4.29));
        carPositions.add(0.0);
        carPositions.add(-5.0);

        // Simple test track
        trackSegments.add(new TrackInfo("Main Straight", 140, "straight", 0.0, 0.5, false));
        trackSegments.add(new TrackInfo("Hairpin", 65, "corner", 0.5, 1.0, true));

        int totalLaps = 3;

        FindTheDriftApp app = new FindTheDriftApp();
        app.simulateRace(cars, carPositions, trackSegments, totalLaps);
    }

    public void simulateRace(List<Car> cars, List<Double> carPositions, List<TrackInfo> trackSegments, int totalLaps) {
        RacePredictor predictor = new RacePredictor();
        Random random = new Random();

        for (int lap = 0; lap < totalLaps; lap++) {
            System.out.println("\n=== Lap " + (lap + 1) + " ===");
            for (int i = 0; i < cars.size(); i++) {
                Car playerCar = cars.get(i);
                Car frontCar = (i > 0) ? cars.get(i - 1) : null;
                int segmentIndex = getCurrentSegmentIndex(carPositions.get(i), trackSegments);
                TrackInfo currentSegment = trackSegments.get(segmentIndex);

                double trackPosition = (carPositions.get(i) - (segmentIndex == 0 ? 0 : trackSegments.get(segmentIndex - 1).getSegmentEnd()))
                        / (currentSegment.getSegmentEnd() - currentSegment.getSegmentStart());
                double spaceToFront = (frontCar != null) ? carPositions.get(i) - carPositions.get(i - 1) : 999;
                int currentGear = playerCar.getGear();

                // Simulate front car mistake
                if (frontCar != null && currentSegment.isCorner()) {
                    boolean mistake = random.nextDouble() < 0.18;
                    frontCar.setMadeMistake(mistake);
                }

                RacePredictor.Decision decision = predictor.predictMove(
                        playerCar, frontCar, currentSegment, trackPosition, spaceToFront, currentGear
                );

                // Apply decision
                playerCar.setGear(decision.gear);
                playerCar.setDrifting(decision.drift);

                // Wall skim simulation
                if (currentSegment.isCorner() && random.nextDouble() < 0.09) {
                    System.out.println(playerCar.getModelName() + " skims the barrier at high speed—steady hands, no fear!");
                }

                // Overtake & "paint trading"
                if ("OVERTAKE_LINE".equals(decision.action)) {
                    if (frontCar != null && carPositions.get(i) < carPositions.get(i - 1)) {
                        double combinedHalfWidths = (playerCar.getWidth() + frontCar.getWidth()) / 2.0;
                        double temp = carPositions.get(i);
                        carPositions.set(i, carPositions.get(i - 1) + 1.0);
                        carPositions.set(i - 1, temp);
                        double minorContactGap = 0.5;

                        if (spaceToFront <= combinedHalfWidths + minorContactGap) {
                            System.out.println(playerCar.getModelName() + " trades paint with " + frontCar.getModelName() +
                                    " (Widths: " + playerCar.getWidth() + "m, " + frontCar.getWidth() + "m)!");
                        } else {
                            System.out.println(playerCar.getModelName() + " overtakes " + frontCar.getModelName() +
                                    " on the optimal line! (Clearance: " +
                                    String.format("%.2f", spaceToFront - combinedHalfWidths) + "m)");
                        }
                    }
                } else if ("BACK_OFF".equals(decision.action)) {
                    playerCar.brakeEarly();
                }

                // Simple position update for the test
                carPositions.set(i, carPositions.get(i) + playerCar.getSpeed() * 0.01);

                // Output for every car every lap
                System.out.println("Car " + playerCar.getModelName() + ": " + decision);
            }
        }
    }

    // Helper method to get segment index based on position
    private int getCurrentSegmentIndex(double position, List<TrackInfo> trackSegments) {
        for (int idx = 0; idx < trackSegments.size(); idx++) {
            TrackInfo seg = trackSegments.get(idx);
            if (position >= seg.getSegmentStart() && position <= seg.getSegmentEnd()) {
                return idx;
            }
        }
        return 0; // fallback
    }

    private double getTrackTotalLength(List<TrackInfo> segments) {
        if (segments.isEmpty()) return 1.0;
        return segments.get(segments.size() - 1).getSegmentEnd();
    }
}



