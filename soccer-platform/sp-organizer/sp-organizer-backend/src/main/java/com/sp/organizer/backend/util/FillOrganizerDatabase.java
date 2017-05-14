package com.sp.organizer.backend.util;

import com.sp.organizer.backend.club.service.ClubService;
import com.sp.core.backend.DateUtil;
import com.sp.core.backend.value.club.ClubInfo;
import com.sp.core.backend.value.match.event.CardEvent;
import com.sp.core.backend.value.match.event.GoalEvent;
import com.sp.core.backend.value.match.event.MatchEvent;
import com.sp.organizer.backend.competition.domain.CompetitionInfo;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import com.sp.organizer.backend.competition.domain.rule.SortingRule;
import com.sp.organizer.backend.competition.domain.season.Stage;
import com.sp.organizer.backend.competition.domain.season.Turn;
import com.sp.organizer.backend.competition.service.CompetitionService;
import io.eventuate.EntityWithIdAndVersion;
import com.sp.organizer.backend.match.domain.MatchAggregator;
import com.sp.core.backend.value.match.MatchInfo;
import com.sp.organizer.backend.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FillOrganizerDatabase {
	
	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private MatchService matchService;
	
	private String[] firstNames = {"Hajdu", "Kiss", "Nagy", "Szilágyi", "Talpas", "Gera", "Szabó", "Bihari", "Sebestyén", "Pintér", "Kádár", "Jelinek", "Szőllősi", "Gittinger", "Galina", "Bócsi"};
	private String[] secondNames = {"László", "István", "Zoltán", "Tamás", "Ádám", "János", "Gergő", "Szilárd", "Tibor", "Attila", "Béla","Róbert", "Kálmán", "Albert", "Balázs", "Sándor"};
	private List<String> clubIds = new ArrayList<>();

	public void fillTestMemoryDB() throws InterruptedException, ExecutionException {
		CompetitionInfo competition = new CompetitionInfo("Kelet Magyarország");
		List<SortingRule> sortingRules = Arrays.asList(SortingRule.GAMES_WON, SortingRule.GOAL_DIFFERENCE,
				SortingRule.GOAL_SCORED, SortingRule.RESULTS_BETWEEN_TEAMS);
		CompetitionRuleSet competitionRules = CompetitionRuleSet.builder().numberOfMatches(30).numberOfTeams(15)
				.promotion(1).relegation(2).yellowCardLimit(5).sortingRules(sortingRules).build();
		Stage stage = Stage.builder(competition.getName()).build();
		String competitionId = competitionService.addCompetition(competition, competitionRules, stage).get().getEntityId();

		
		ClubInfo vamosoroszi = new ClubInfo("Vámosoroszi Községi Sport Egyesület","Vámosoroszi KSE");
		String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
		clubIds.add(vamosoroszId);

		ClubInfo tarpa = new ClubInfo("Tarpa Sport Club","Tarpa SC");
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();
		clubIds.add(tarpaId);

		ClubInfo tisztaberek = new ClubInfo("Tisztaberek Sport Egyesulet","Tisztaberek SE");
		String tisztaberekId = clubService.addClub(tisztaberek).get().getEntityId();
		clubIds.add(tisztaberekId);

		ClubInfo szatmarcseke = new ClubInfo("Szatmarcseke Községi Sport Egyesület","Szatmarcseke KSE");
		String szatmarcsekeId = clubService.addClub(szatmarcseke).get().getEntityId();
		clubIds.add(szatmarcsekeId);

		ClubInfo tyukod = new ClubInfo("Tyukod Footbal Club", "Tyukod FC");
		String tyukodId = clubService.addClub(tyukod).get().getEntityId();
		clubIds.add(tyukodId);

		ClubInfo csengersimaert = new ClubInfo("Csengersimaert Községi Sport Egyesület","Csengersimaert KSE");
		String csengersimaertId = clubService.addClub(csengersimaert).get().getEntityId();
		clubIds.add(csengersimaertId);

		ClubInfo nyirmeggyes = new ClubInfo("Nyirmeggyes Sportklub","Nyirmeggyes SK");
		String nyirmeggyesId = clubService.addClub(nyirmeggyes).get().getEntityId();
		clubIds.add(nyirmeggyesId);

		ClubInfo tiszakorod = new ClubInfo("Tiszakorod Sport Egyesulet","Tiszakorod SE");
		String tiszakorodId = clubService.addClub(tiszakorod).get().getEntityId();
		clubIds.add(tiszakorodId);

		ClubInfo nabrad = new ClubInfo("Nabrad Sport Egyesulet","Nabrad SE");
		String nabradId = clubService.addClub(nabrad).get().getEntityId();
		clubIds.add(nabradId);

		ClubInfo Beregdaroc = new ClubInfo("Beregdaroc Sport Egyesulet","Beregdaroc SE");
		String beregdarocId = clubService.addClub(Beregdaroc).get().getEntityId();
		clubIds.add(beregdarocId);

		ClubInfo csenger = new ClubInfo("Csenger Footbal Club","Csenger FC");
		String csengerId = clubService.addClub(csenger).get().getEntityId();
		clubIds.add(csengerId);

		ClubInfo kolcse = new ClubInfo("Kolcse Sport Egyesulet","Kolcse SE");
		String kolcseId = clubService.addClub(kolcse).get().getEntityId();
		clubIds.add(kolcseId);

		ClubInfo nagydobosi = new ClubInfo("Nagydobosi Labdarugo Sport Egyesulet","Nagydobosi LSE");
		String nagydobosiId = clubService.addClub(nagydobosi).get().getEntityId();
		clubIds.add(nagydobosiId);

		ClubInfo milota = new ClubInfo("Milota Sport Egyesulet","Milota SE");
		String milotaId = clubService.addClub(milota).get().getEntityId();
		clubIds.add(milotaId);

		competitionService.registerClubsToCompetition(competitionId, clubIds.toArray(new String[clubIds.size()])).get();
		
		List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds = new ArrayList<>();
		for(int i=0; i< clubIds.size() - 1; ++i) {
			List<String> firstHalfClubIds = new ArrayList<>(clubIds.subList(0, clubIds.size() / 2));
			List<String> seacondHalfClubIds = new ArrayList<>(clubIds.subList(clubIds.size() / 2, clubIds.size()));
			Collections.reverse(seacondHalfClubIds);
			Turn turn = Turn.builder(i+1).build();
			List<MatchInfo> matchInfos = new ArrayList<>();
			for (int j=0; j<firstHalfClubIds.size(); ++j) {
				String homeClubId = "";
				String awayClubId = "";
				if(i % 2 == 0) {
					homeClubId = firstHalfClubIds.get(j);
					awayClubId = seacondHalfClubIds.get(j);
				} else {
					homeClubId = seacondHalfClubIds.get(j);
					awayClubId = firstHalfClubIds.get(j);
				}
				MatchInfo matchInfo = MatchInfo.builder(homeClubId, awayClubId, LocalDateTime.of(2017, 1, 9, 16, 00).plusWeeks(i).format(DateUtil.dateTimeFormatter))
						.competitionId(competitionId)
						.stageName(competition.getName())
						.index(turn.getIndex())
						.build();
				EntityWithIdAndVersion<MatchAggregator> matchWithId = matchService.addMatch(matchInfo).get();
				matchWithIds.add(matchWithId);
				matchInfos.add(matchInfo);
				turn.getMatches().add(matchWithId.getEntityId());
			}
			
			Turn reTurn = Turn.builder(turn.getIndex() + (clubIds.size() - 1)).build();
			new ArrayList<>(matchInfos).stream().forEach(match -> {
				LocalDateTime plusMonths = LocalDateTime.parse(match.getMatchDate(), DateUtil.dateTimeFormatter).plusMonths(3);
				MatchInfo matchInfo = MatchInfo.builder(match.getAwayClubId(), match.getHomeClubId(), plusMonths.format(DateUtil.dateTimeFormatter))		
						.competitionId(competitionId)
						.stageName(competition.getName())
						.index(turn.getIndex())
						.build();
				try {
					EntityWithIdAndVersion<MatchAggregator> matchWithId = matchService.addMatch(matchInfo).get();
					matchWithIds.add(matchWithId);
					reTurn.getMatches().add(matchWithId.getEntityId());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			});
			
			clubIds.add(1, clubIds.get(clubIds.size()-1));
			clubIds.remove(clubIds.size()-1);
		}
		
		 fillTurnWithRandomEvents(matchWithIds, competitionId);

	}

	private void fillTurnWithRandomEvents(List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds, String competitionId) throws InterruptedException, ExecutionException {
		for (EntityWithIdAndVersion<MatchAggregator> matchWithId : matchWithIds) {
			MatchInfo matchInfo = matchWithId.getAggregate().getMatchInfo();
			List<MatchEvent> homeEvents = new ArrayList<>();
			List<GoalEvent> randomGoalEventsHome = randomGoalEvents(matchInfo.getHomeClubId());
			List<CardEvent> randomYellowCardEventsHome = randomYellowCardEvents(matchInfo.getHomeClubId());
			List<CardEvent> randomRedCardEventsHome = randomRedCardEvents(matchInfo.getHomeClubId());

			homeEvents.addAll(randomGoalEventsHome);
			homeEvents.addAll(randomYellowCardEventsHome);
			homeEvents.addAll(randomRedCardEventsHome);

			List<MatchEvent> awayEvents = new ArrayList<>();
			List<GoalEvent> randomGoalEvents = randomGoalEvents(matchInfo.getAwayClubId());
			List<CardEvent> randomYellowCardEvents = randomYellowCardEvents(matchInfo.getAwayClubId());
			List<CardEvent> randomRedCardEvents = randomRedCardEvents(matchInfo.getAwayClubId());

			awayEvents.addAll(randomGoalEvents);
			awayEvents.addAll(randomYellowCardEvents);
			awayEvents.addAll(randomRedCardEvents);

			matchService.playMatch(competitionId, matchWithId.getEntityId(), homeEvents, awayEvents).get();
		}
	}
	
	private List<GoalEvent> randomGoalEvents(String clubId) {
		int goalAmount = new Random().nextInt(4);
		List<GoalEvent> goals = new ArrayList<>();
		for (int i = 0; i < goalAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			goals.add(GoalEvent.goalOf(minute));
		}
		return goals;
	}
	
	private List<CardEvent> randomYellowCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(4);
		List<CardEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.yellowCardOf(minute));
		}
		return cards;
	}
	
	private List<CardEvent> randomRedCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(2);
		List<CardEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.redCardOf(minute));
		}
		return cards;
	}
	
}
