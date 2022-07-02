package pl.janda.memory.model.ranking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RankingService {

    private static final String RANKING_fILE_PATH = "ranking.txt";

    public List<RankingRecord> loadRanking() {
        return loadFromFile(new File(RANKING_fILE_PATH));
    }

    public void saveRankingRecord(RankingRecord record) {
        File file = new File(RANKING_fILE_PATH);
        List<RankingRecord> rankingRecords = loadFromFile(file);
        rankingRecords.add(record);
        saveToFile(file, rankingRecords);
    }

    private void saveToFile(File file, List<RankingRecord> rankingRecords) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            for (RankingRecord menuPosition : rankingRecords) {
                objectOutputStream.writeObject(menuPosition);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Blad podczas zapisu do pliku " + file.getName() + ": " + e.getMessage());
        }
    }

    private List<RankingRecord> loadFromFile(File file) {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        List<RankingRecord> loadedRecords = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while (inputStream.available() > 0) {
                loadedRecords.add((RankingRecord) objectInputStream.readObject());
            }
            inputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Blad podczas wczytywania z pliku " + file.getName() + ": " + e.getMessage());
        }
        return loadedRecords;
    }

}
