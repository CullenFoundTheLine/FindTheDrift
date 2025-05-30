public class LapTimeConverter {
    public static void main(String[] args) {
        int[] lapTimesInSeconds = {95, 134, 203, 360, 452}; // example lap times in seconds

        for (int i = 0; i < lapTimesInSeconds.length; i++) {
            int minutes = lapTimesInSeconds[i] / 60;
            int seconds = lapTimesInSeconds[i] % 60;

            System.out.println("Lap " + (i + 1) + ": " + minutes + "m " + seconds + "s");
        }
    }
}
