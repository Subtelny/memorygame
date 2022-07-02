package pl.janda.memory.controller;

import pl.janda.memory.model.ranking.RankingService;
import pl.janda.memory.view.RankingRecordView;

import java.util.Comparator;
import java.util.List;

public class RankingReaderControllerImpl implements RankingReaderController {

    private final RankingService rankingService;

    public RankingReaderControllerImpl(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public List<RankingRecordView> getRecords() {
        return rankingService.loadRanking().stream()
                .map(record -> RankingRecordView.from(record.name(), record.gridSize(), record.time(), record.score()))
                .sorted((o1, o2) -> Double.compare(o2.score(), o1.score()))
                .toList();
    }
}
