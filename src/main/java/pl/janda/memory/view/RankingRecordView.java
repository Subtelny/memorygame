package pl.janda.memory.view;

public record RankingRecordView(String playerName, String gridSize, String time, double score) {

    public static RankingRecordView from(String playerName, int gridSize, int seconds, double score) {
        int minutes = seconds / 60;
        int sec = seconds % 60;
        String time = String.format("%d:%02d", minutes, sec);
        return new RankingRecordView(
                playerName,
                gridSize + "x" + gridSize,
                time,
                score
        );
    }

    public String[] toArr() {
        return new String[]{playerName(), time(), gridSize(), score() + ""};
    }
}
