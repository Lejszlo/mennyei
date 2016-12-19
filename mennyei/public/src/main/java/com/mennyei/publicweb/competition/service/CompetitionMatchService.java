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
import com.mennyei.publicweb.competition.dto.MatchQuery;
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
		MatchQuery matchQuery = MatchQuery.builder().index(turn.getIndex()).build();
		modelMapper.map(match, matchQuery);
		if(matchQuery.isPlayed()) {
			matchQuery.setResultType(match.getResult().whoIsTheWinner());
		}
		ClubQuery clubQuery = clubQueryMongoRepository.findOne(match.whoIsTheOpponentOf(clubId));
		matchQuery.setOpponentClubId(clubQuery);
		matchQuery.setAtHome(match.isAtHome(clubId));
		matchQuery.setCompetitionName(competitionQuery.getName());
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
	
	private Match getMatch(CompetitionQuery competitionQuery, String stageName, int turnIndex, String homeClubId) {
		Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(stageName)).findFirst();
        Optional<Turn> turn = stage.get().getTurnByIndex(turnIndex);
		Optional<Match> matchOptional = turn.get().findMatchByClub(homeClubId);
		return matchOptional.get();
	}

}
