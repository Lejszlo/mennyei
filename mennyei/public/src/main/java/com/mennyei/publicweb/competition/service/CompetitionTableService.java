package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.table.TableQuery;
import com.mennyei.publicweb.competition.dto.table.TableRowQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import com.mennyei.publicweb.competition.infrastructure.TableMongoRepository;
import com.mennyei.publicweb.match.dto.MatchQuery;

@Service
public class CompetitionTableService {

	@Autowired
	private TableMongoRepository tableMongoRepository;

	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;

	public void refreshTable(MatchQuery matchQuery) {
		TableQuery tableQuery = tableMongoRepository.findByCompetitionIdAndStageName(matchQuery.getCompetition().getId(), matchQuery.getStageName());
		Optional<TableRowQuery> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().equals(matchQuery.getHomeClub())).findFirst();
		Optional<TableRowQuery> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClub().equals(matchQuery.getAwayClub())).findFirst();
		
		updateRow(homeClubTableRowOptional, matchQuery);
		updateRow(awayClubTableRowOptional, matchQuery);
		
		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(Optional<TableRowQuery> tableRowOptional, MatchQuery matchQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRowQuery tableRow = tableRowOptional.get();
		tableRow.setResult(matchQuery.getResultFor(tableRow.getClub()));
		tableRow.addScoredGoals(matchQuery.getGoalAmountFor(tableRow.getClub()));
		tableRow.addConcerdGoals(matchQuery.getGoalAmountFor(matchQuery.whoIsTheOpponentOf(tableRow.getClub())));
		tableRow.calculatePoints(matchQuery.getCompetition().getCompetitionRuleSet());
		tableRow.incraseMatches();
	}

	public TableQuery getCompetationTable(String competitionId, String stageName) {
		// competitionMongoRepository.findOne(competitionId);
		CompetitionQuery competitionQuery = competitionMongoRepository.findAll().get(0);
		Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(stageName)).findFirst();
		return tableMongoRepository.findByCompetitionIdAndStageName(competitionQuery.getId(), stageName);
	}

	public void createTables(List<Stage> stages, CompetitionQuery competitionQuery) {
		stages.stream().forEach(s -> {
				TableQuery tableQuery = TableQuery.builder(competitionQuery, s.getName()).build();
				tableMongoRepository.save(tableQuery);
		});
	}

	public void createTableRow(List<ClubQuery> clubQueries, CompetitionQuery competitionQuery) {
		TableQuery tableQuery = tableMongoRepository.findByCompetitionIdAndStageName(competitionQuery.getId(), competitionQuery.getStages().get(0).getName());
		
		if(tableQuery == null) {
			return;
		}
		
		clubQueries.stream().filter(Objects::nonNull).forEach(cq -> tableQuery.getRows().add(new TableRowQuery(cq)));
		tableMongoRepository.save(tableQuery);
	}

}
