package com.sp.organizer.query.updater.competition.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.updater.competition.entity.TableQuery;
import com.sp.organizer.query.updater.competition.entity.TableRowQuery;
import com.sp.organizer.query.updater.competition.repository.StageDocumentMongoRepository;
import com.sp.organizer.query.updater.competition.repository.TableQueryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.match.api.event.MatchPlayed;
import com.sp.organizer.api.value.competition.StageId;

import static com.sp.organizer.api.value.club.ClubId.clubId;

@Service
public class CompetitionTableService {

	private final TableQueryMongoRepository tableMongoRepository;

	private final StageDocumentMongoRepository stageDocumentMongoRepository;

    @Autowired
    public CompetitionTableService(TableQueryMongoRepository tableMongoRepository,
                                   StageDocumentMongoRepository stageDocumentMongoRepository) {
        this.tableMongoRepository = tableMongoRepository;
        this.stageDocumentMongoRepository = stageDocumentMongoRepository;
    }

    public void refreshTable(MatchPlayed matchPlayed, StageDocument stageDocument) {
		TableQuery tableQuery = stageDocument.getTableQuery();

		tableQuery.getRows().stream()
				.filter(r -> clubId(r.getClub().getId()).equals(matchPlayed.getHomeClubId()))
                .findFirst()
                .ifPresent(tableRowQuery -> updateRow(tableRowQuery, matchPlayed, stageDocument));
		tableQuery.getRows().stream()
                .filter(r -> clubId(r.getClub().getId()).equals(matchPlayed.getAwayClubId()))
                .findFirst()
                .ifPresent(tableRowQuery -> updateRow(tableRowQuery, matchPlayed, stageDocument));

		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(TableRowQuery tableRow, MatchPlayed matchPlayed, StageDocument stageDocument) {
		tableRow.setResult(matchPlayed.getResultFor(clubId(tableRow.getClub().getId())));
		tableRow.addScoredGoals(matchPlayed.getGoalAmountFor(clubId(tableRow.getClub().getId())));
		tableRow.addConcerdGoals(matchPlayed.getGoalAmountFor(matchPlayed.whoIsTheOpponentOf(clubId(tableRow.getClub().getId()))));
		tableRow.calculatePoints(stageDocument.getStageRuleSet());
		tableRow.incraseMatches();
	}

	public void createTables(StageDocument stageDocument) {
        List<TableRowQuery> tableRowQueries = stageDocument.getClubs()
                .stream()
                .map(TableRowQuery::new)
                .collect(Collectors.toList());

        TableQuery tableQuery = TableQuery.builder()
                .rows(tableRowQueries)
                .build();

        tableMongoRepository.save(tableQuery);
		stageDocument.setTableQuery(tableQuery);
	}

	public StageDocument getStageTable(StageId stageId) {
		return stageDocumentMongoRepository.findByCompetitionIdAndStageIndex(stageId.getCompetitionId().getValue(), stageId.getIndex());
	}

}
