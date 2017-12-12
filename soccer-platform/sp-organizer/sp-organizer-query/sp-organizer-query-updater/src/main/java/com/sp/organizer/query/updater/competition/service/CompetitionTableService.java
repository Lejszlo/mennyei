package com.sp.organizer.query.updater.competition.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;
import com.sp.organizer.query.updater.competition.entity.TableQuery;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import com.sp.organizer.query.updater.match.entity.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.updater.competition.entity.TableRowQuery;
import com.sp.organizer.query.updater.competition.repository.TableQueryMongoRepository;

@Service
public class CompetitionTableService {

	@Autowired
	private TableQueryMongoRepository tableMongoRepository;

	@Autowired
	private CompetitionQueryMongoRepository competitionMongoRepository;

	public void refreshTable(MatchQuery matchQuery, StageQuery stageQuery) {
		TableQuery tableQuery = stageQuery.getTableQuery();
		Optional<TableRowQuery> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().getId().equals(matchQuery.getHomeClub().getId())).findFirst();
		Optional<TableRowQuery> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().getId().equals(matchQuery.getAwayClub().getId())).findFirst();
		
		updateRow(homeClubTableRowOptional, matchQuery, stageQuery);
		updateRow(awayClubTableRowOptional, matchQuery, stageQuery);
		
		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(Optional<TableRowQuery> tableRowOptional, MatchQuery matchQuery, StageQuery stageQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRowQuery tableRow = tableRowOptional.get();
		tableRow.setResult(matchQuery.getResultFor(tableRow.getClub().getId()));
		tableRow.addScoredGoals(matchQuery.getGoalAmountFor(tableRow.getClub().getId()));
		tableRow.addConcerdGoals(matchQuery.getGoalAmountFor(matchQuery.whoIsTheOpponentOf(tableRow.getClub().getId()).getId()));
		tableRow.calculatePoints(stageQuery.getStageRuleSet());
		tableRow.incraseMatches();
	}

	public void createTables(StageQuery stageQuery) {
        List<TableRowQuery> tableRowQueries = stageQuery.getClubs()
                .stream()
                .map(TableRowQuery::new)
                .collect(Collectors.toList());

        TableQuery tableQuery = TableQuery.builder()
                .rows(tableRowQueries)
                .build();

        tableMongoRepository.save(tableQuery);
		stageQuery.setTableQuery(tableQuery);
	}

	public List<TableQuery> getCompetitionTable(String competitionId) {
		CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
		return competitionQuery.getStages().stream().map(StageQuery::getTableQuery).collect(Collectors.toList());
	}

}
