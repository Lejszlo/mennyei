package com.mennyei.publicweb.competition.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.table.TableQuery;
import com.mennyei.publicweb.competition.dto.table.TableRow;
import com.mennyei.publicweb.competition.infrastructure.TableMongoRepository;

@Service
public class CompetitionTableService {

	@Autowired
	private TableMongoRepository tableMongoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public void refreshTable(CompetitionQuery competitionQuery, Match match, String stageName) {
		TableQuery tableQuery = tableMongoRepository.findByCompetitionIdAndStageName(competitionQuery.getId(), stageName);
		
		Optional<TableRow> homeClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(match.getHomeClubId())).findFirst();
		Optional<TableRow> awayClubTableRowOptional = tableQuery.getRows().stream().filter(r -> r.getClubId().equals(match.getAwayClubId())).findFirst();
		
		updateRow(homeClubTableRowOptional, match, competitionQuery);
		updateRow(awayClubTableRowOptional, match, competitionQuery);
	}

	private void updateRow(Optional<TableRow> tableRowOptional, Match match, CompetitionQuery competitionQuery) {
		if(!tableRowOptional.isPresent())  {
			return;
		}
		
		TableRow tableRow = tableRowOptional.get();
		tableRow.setResult(match.getResultFor(tableRow.getClubId()));
		tableRow.addScoredGoals(match.getGoalAmountFor(tableRow.getClubId()));
		tableRow.addConcerdGoals(match.getGoalAmountFor(match.whoIsTheOpponentOf(tableRow.getClubId())));
		tableRow.calculatePoints(competitionQuery.getCompetitionRules());
		tableRow.incraseMatches();
	}
}
