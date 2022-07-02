package pl.janda.memory.controller;

import pl.janda.memory.view.RankingRecordView;

import java.util.List;

public interface RankingReaderController {

    List<RankingRecordView> getRecords();

}
