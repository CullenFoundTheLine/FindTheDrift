public class GhostSystem {
    private final GhostVoice voice = new GhostVoice();
    private final GhostMind mind = new GhostMind();
    private final GhostHUD hud = new GhostHUD();
    private final GhostMemory memory = new GhostMemory();

    public void runSession(RaceData data, Car car, TrackInfo track, ECUSettings ecu) {
        String command = voice.listen();
        mind.analyze(data, command, car, track, ecu);
        hud.updateDisplay(data, ecu);
        memory.updateProfile(data);
        voice.speak(mind.getAdvice());
    }
}