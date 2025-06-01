
public class GhostVoice {
    public String listen() {
        return "Status check.";
    }

    public void speak(String response) {
        System.out.println("Ghost says: " + response);
    }
}
