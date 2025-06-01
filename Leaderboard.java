import java.util.*;

public class Leaderboard {
    private List<Record> records = new ArrayList<>();

    public void addRecord(String player, double lapTime, String carModel) {
        records.add(new Record(player, lapTime, carModel));
        records.sort(Comparator.comparingDouble(r -> r.lapTime));
    }

    public void showTop(int n) {
        System.out.println("Leaderboard - Top " + n);
        for (int i = 0; i < Math.min(n, records.size()); i++) {
            Record rec = records.get(i);
            System.out.println((i + 1) + ". " + rec.player + " - " + rec.lapTime + "s - " + rec.carModel);
        }
    }

    public static class Record {
        String player;
        double lapTime;
        String carModel;

        public Record(String player, double lapTime, String carModel) {
            this.player = player;
            this.lapTime = lapTime;
            this.carModel = carModel;
        }
    }
}


