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
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.MatchQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

@Service
public class CompetitionBusinessService {

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

		return stage.get().getTurns().stream().map(t -> createMatchQuery(t, clubId2)).collect(Collectors.toList());
	}

	private MatchQuery createMatchQuery(Turn turn, String clubId) {
		Optional<Match> match = turn.getMatches().stream().filter(m -> m.containsMatch(clubId)).findFirst();
		return mapMatch(match.get(), turn, clubId);
	}

	private MatchQuery mapMatch(Match match, Turn turn, String clubId) {
		MatchQuery matchQuery = MatchQuery.builder().index(turn.getIndex()).build();
		modelMapper.map(match, matchQuery);
		if(matchQuery.isPlayed()) {
			matchQuery.setResultType(match.getResult().whoIsTheWinner());
		}
		ClubQuery clubQuery = clubQueryMongoRepository.findOne(match.whoIsTheOpponentOf(clubId));
		matchQuery.setOpponentClubId(clubQuery);
		matchQuery.setAtHome(match.isAtHome(clubId));
		return matchQuery;
	}

}
