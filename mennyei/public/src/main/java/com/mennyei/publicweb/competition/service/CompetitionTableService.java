package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.table.TableQuery;
import com.mennyei.publicweb.competition.dto.table.TableRowQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import com.mennyei.publicweb.competition.infrastructure.TableMongoRepository;

@Service
public class CompetitionTableService {

	@Autowired
	private TableMongoRepository tableMongoRepository;

	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;

	public void refreshTable(CompetitionQuery competitionQuery, Match match, String stageName) {
		TableQuery tableQuery = tableMongoRepository.findByCompetitionIdAndStageName(competitionQuery.getId(),
				stageName);

		Optional<TableRowQuery> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(match.getHomeClubId())).findFirst();
		Optional<TableRowQuery> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(match.getAwayClubId())).findFirst();
		
		updateRow(homeClubTableRowOptional, match, competitionQuery);
		updateRow(awayClubTableRowOptional, match, competitionQuery);
		
		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(Optional<TableRowQuery> tableRowOptional, Match match, CompetitionQuery competitionQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRowQuery tableRow = tableRowOptional.get();
		tableRow.setResult(match.getResultFor(tableRow.getClubId()));
		tableRow.addScoredGoals(match.getGoalAmountFor(tableRow.getClubId()));
		tableRow.addConcerdGoals(match.getGoalAmountFor(match.whoIsTheOpponentOf(tableRow.getClubId())));
		tableRow.calculatePoints(competitionQuery.getCompetitionRuleSet());
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
		clubQueries.stream().forEach(cq -> tableQuery.getRows().add(new TableRowQuery(cq.getId(), cq.getName())));
		tableMongoRepository.save(tableQuery);
	}

}
