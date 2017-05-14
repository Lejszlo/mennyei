package com.sp.organizer.backend.competition.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;
import com.sp.organizer.backend.competition.dto.stage.StageQuery;
import com.sp.organizer.backend.competition.dto.table.TableQuery;
import com.sp.organizer.backend.competition.dto.table.TableRowQuery;
import com.sp.organizer.backend.competition.infrastructure.CompetitionQueryMongoRepository;
import com.sp.organizer.backend.competition.infrastructure.TableMongoRepository;
import com.sp.organizer.backend.match.dto.match.MatchQuery;

@Service
public class CompetitionTableService {

	@Autowired
	private TableMongoRepository tableMongoRepository;

	@Autowired
	private CompetitionQueryMongoRepository competitionMongoRepository;

	public void refreshTable(MatchQuery matchQuery) {
		TableQuery tableQuery = matchQuery.getCompetition().getStages().get(0).getTableQuery();
		Optional<TableRowQuery> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().getId().equals(matchQuery.getHomeClub().getId())).findFirst();
		Optional<TableRowQuery> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().getId().equals(matchQuery.getAwayClub().getId())).findFirst();
		
		updateRow(homeClubTableRowOptional, matchQuery);
		updateRow(awayClubTableRowOptional, matchQuery);
		
		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(Optional<TableRowQuery> tableRowOptional, MatchQuery matchQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRowQuery tableRow = tableRowOptional.get();
		tableRow.setResult(matchQuery.getResultFor(tableRow.getClub().getId()));
		tableRow.addScoredGoals(matchQuery.getGoalAmountFor(tableRow.getClub().getId()));
		tableRow.addConcerdGoals(matchQuery.getGoalAmountFor(matchQuery.whoIsTheOpponentOf(tableRow.getClub().getId()).getId()));
		tableRow.calculatePoints(matchQuery.getCompetition().getCompetitionRuleSet());
		tableRow.incraseMatches();
	}

	public void createTables(CompetitionQuery competitionQuery) {
		competitionQuery.getStages().stream().forEach(s -> {
				TableQuery tableQuery = TableQuery.builder().build();
				s.setTableQuery(tableQuery);
				tableMongoRepository.save(tableQuery);
		});
	}

	public void createTableRow(List<ClubQuery> clubQueries, CompetitionQuery competitionQuery) {
		TableQuery tableQuery = competitionQuery.getStages().get(0).getTableQuery();
		
		if(tableQuery == null) {
			return;
		}
		
		clubQueries.stream().filter(Objects::nonNull).forEach(cq -> tableQuery.getRows().add(new TableRowQuery(cq)));
		tableMongoRepository.save(tableQuery);
	}

	public List<TableQuery> getCompetationTable(String competitionId) {
		CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
		return competitionQuery.getStages().stream().map(StageQuery::getTableQuery).collect(Collectors.toList());
	}

}
