public class TrackInfo {
    private String name;
    private double recommendedSpeed;
    private String segmentType; // "corner", "straight", etc.
    private double segmentStart; // 0.0-1.0
    private double segmentEnd;   // 0.0-1.0
    private boolean driftOptimal;

    public TrackInfo(String name, double recommendedSpeed, String segmentType, double segmentStart, double segmentEnd, boolean driftOptimal) {
        this.name = name;
        this.recommendedSpeed = recommendedSpeed;
        this.segmentType = segmentType;
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
        this.driftOptimal = driftOptimal;
    }

    public double getRecommendedSpeed() { return recommendedSpeed; }
    public boolean isCorner() { return "corner".equalsIgnoreCase(segmentType); }
    public boolean isStraight() { return "straight".equalsIgnoreCase(segmentType); }
    public boolean isDriftOptimal() { return driftOptimal; }
    public String getSegmentType() { return segmentType; }
    public double getSegmentStart() { return segmentStart; }
    public double getSegmentEnd() { return segmentEnd; }
    public String getName() { return name; }

    // Track position as 0.0 to 1.0 (start to finish)
    public boolean isEntrySection(double position) {
        return position >= segmentStart && position < (segmentStart + 0.1);
    }
    public boolean isExitSection(double position) {
        return position > (segmentEnd - 0.1) && position <= segmentEnd;
    }
}

