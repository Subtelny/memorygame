package pl.janda.memory.model.ranking;

import java.io.Serializable;

public record RankingRecord(String name, int gridSize, int time, double score) implements Serializable {

}
