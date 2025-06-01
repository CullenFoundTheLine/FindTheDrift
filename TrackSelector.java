import java.util.*;

public class TrackSelector {
    private final Map<String, TrackInfo> tracks = new LinkedHashMap<>();

    public TrackSelector() {
        // Add tracks with: name, recommendedSpeed, segmentType, segmentStart, segmentEnd, driftOptimal
        tracks.put("Suzuka Circuit", new TrackInfo(
                "Suzuka Circuit", 75.0, "corner", 0.0, 1.0, true
        ));
        tracks.put("Nurburgring", new TrackInfo(
                "Nurburgring", 90.0, "mixed", 0.0, 1.0, false
        ));
        tracks.put("Ebisu Drift Park", new TrackInfo(
                "Ebisu Drift Park", 65.0, "corner", 0.0, 1.0, true
        ));
        tracks.put("Miami Drift GP", new TrackInfo(
                "Miami Drift GP", 70.0, "mixed", 0.0, 1.0, true
        ));
        tracks.put("Silverstone", new TrackInfo(
                "Silverstone", 80.0, "mixed", 0.0, 1.0, false
        ));
    }

    public void displayAvailableTracks() {
        System.out.println("\n--- Available Tracks ---");
        int index = 1;
        for (String name : tracks.keySet()) {
            System.out.printf("%2d. %s%n", index++, name);
        }
    }

    public List<String> getAllTrackNames() {
        return new ArrayList<>(tracks.keySet());
    }

    public TrackInfo getTrackInfo(String trackName) {
        return tracks.get(trackName);
    }

    public boolean isValidTrack(String trackName) {
        return tracks.containsKey(trackName);
    }
}

