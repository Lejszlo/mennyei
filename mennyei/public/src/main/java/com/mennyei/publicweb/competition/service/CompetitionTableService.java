package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.event.MatchPlayed;
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

	public void refreshTable(MatchQuery matchQuery, MatchPlayed matchPlayed) {
		TableQuery tableQuery = tableMongoRepository.findByCompetitionIdAndStageName(matchQuery.getCompetition().getId(), matchQuery.getStageName());
		MatchInfo matchInfo = matchPlayed.getMatchInfo();
		Optional<TableRowQuery> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(matchInfo.getHomeClubId())).findFirst();
		Optional<TableRowQuery> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(matchInfo.getAwayClubId())).findFirst();
		
		updateRow(homeClubTableRowOptional, matchInfo, matchQuery.getCompetition());
		updateRow(awayClubTableRowOptional, matchInfo, matchQuery.getCompetition());
		
		tableMongoRepository.save(tableQuery);
	}

	private void updateRow(Optional<TableRowQuery> tableRowOptional, MatchInfo matchInfo, CompetitionQuery competitionQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRowQuery tableRow = tableRowOptional.get();
		tableRow.setResult(matchInfo.getResultFor(tableRow.getClubId()));
		tableRow.addScoredGoals(matchInfo.getGoalAmountFor(tableRow.getClubId()));
		tableRow.addConcerdGoals(matchInfo.getGoalAmountFor(matchInfo.whoIsTheOpponentOf(tableRow.getClubId())));
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
