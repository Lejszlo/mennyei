package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.events.MatchPlayed;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.match.MatchDetailesQuery;
import com.mennyei.publicweb.competition.dto.match.MatchQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

@Service
public class CompetitionMatchService {

	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<MatchQuery> getClubMatches(String clubId, String competitionId, String stageName) {
//		CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
		CompetitionQuery competitionQuery = competitionMongoRepository.findAll().get(0);
		String clubId2 = clubQueryMongoRepository.findByUrlName("vamosoroszikse").getId();

		Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(stageName)).findFirst();

		return stage.get().getTurns().stream().map(t -> createMatchQuery(t, clubId2, competitionQuery)).collect(Collectors.toList());
	}

	private MatchQuery createMatchQuery(Turn turn, String clubId, CompetitionQuery competitionQuery) {
		Optional<Match> match = turn.getMatches().stream().filter(m -> m.containsClub(clubId)).findFirst();
		return mapMatch(match.get(), turn, clubId, competitionQuery);
	}

	private MatchQuery mapMatch(Match match, Turn turn, String clubId, CompetitionQuery competitionQuery) {
		MatchQuery matchQuery = MatchQuery.builder(turn.getIndex()).build();
		matchQuery.setPlayed(match.isPlayed());
		matchQuery.setMatchDate(match.getMatchDate());
		matchQuery.setFans(match.getFans());
		if(matchQuery.isPlayed()) {
			matchQuery.setScoredGoalAmount(match.getScoredGoalAmount(clubId));
			matchQuery.setConcernGoalAmount(match.getConcernedGoalAmount(clubId));
			matchQuery.setMatchResult(match.getResultFor(clubId));
			matchQuery.setResult(match.getResult());
		}
		ClubQuery opponentClubQuery = clubQueryMongoRepository.findOne(match.whoIsTheOpponentOf(clubId));
		ClubQuery clubQuery = clubQueryMongoRepository.findOne(match.whoIsMe(clubId));
		matchQuery.setClubId(clubQuery);
		matchQuery.setOpponentClubId(opponentClubQuery);
		matchQuery.setAtHome(match.isAtHome(clubId));
		matchQuery.setCompetitionName(competitionQuery.getCompetitionInfo().getName());
		return matchQuery;
	}

	public Match matchPlayed(MatchPlayed event, CompetitionQuery competitionQuery) {
		Match match = getMatch(competitionQuery, event.getStageName(), event.getTurnIndex(), event.getHomeClubId());
		match.getHomeClubevents().addAll(event.getHomeClubEvents());
		match.getAwayClubevents().addAll(event.getAwayClubEvents());
		match.calculateResult();
		match.setPlayed(true);
		return match;
	}
	
	public MatchDetailesQuery getCompetationMatchDetailes(String competitionId, String stageName, int turnIndex, String clubId) {
		CompetitionQuery competitionQuery = competitionMongoRepository.findAll().get(0);
		Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(stageName)).findFirst();
		Match match = getMatch(competitionQuery, stageName, turnIndex, clubId);
		Optional<Turn> turn = stage.get().getTurnByIndex(turnIndex);
		MatchQuery matchQuery = mapMatch(match,turn.get(), clubId, competitionQuery);
		MatchDetailesQuery matchDetailesQuery = MatchDetailesQuery.builder(matchQuery).build();
		matchDetailesQuery.setTotalRedCardAmount(match.calculateTotalRedCardAmount());
		matchDetailesQuery.setTotalYellowCardAmount(match.calculateTotalYellowCardAmount());
		return matchDetailesQuery;
	}
	
	private Match getMatch(CompetitionQuery competitionQuery, String stageName, int turnIndex, String homeClubId) {
		Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(stageName)).findFirst();
        Optional<Turn> turn = stage.get().getTurnByIndex(turnIndex);
		Optional<Match> matchOptional = turn.get().findMatchByClub(homeClubId);
		return matchOptional.get();
	}
	
}
