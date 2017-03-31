package com.mennyei.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.service.ClubService;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.core.competition.domain.rule.SortingRule;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.service.CompetitionService;
import com.mennyei.core.match.domain.MatchAggregator;
import com.mennyei.core.match.domain.MatchInfo;
import com.mennyei.core.match.domain.event.CardEvent;
import com.mennyei.core.match.domain.event.GoalEvent;
import com.mennyei.core.match.domain.event.MatchEvent;
import com.mennyei.core.match.domain.event.SubstitutionEvent;
import com.mennyei.core.match.domain.event.lineup.LineUp;
import com.mennyei.core.match.service.MatchService;
import com.mennyei.core.player.domain.Player;
import com.mennyei.core.player.service.PlayerService;
import com.mennyei.core.transfer.domain.Transfer;
import com.mennyei.core.transfer.service.TransferService;

import io.eventuate.EntityWithIdAndVersion;

@Service
public class FillDatabase {
	
	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private TransferService transferService;

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private MatchService matchService;
	
	private String[] firstNames = {"Hajdu", "Kiss", "Nagy", "Szilágyi", "Talpas", "Gera", "Szabó", "Bihari", "Sebestyén", "Pintér", "Kádár", "Jelinek", "Szőllősi", "Gittinger", "Galina", "Bócsi"};
	private String[] secondNames = {"László", "István", "Zoltán", "Tamás", "Ádám", "János", "Gergő", "Szilárd", "Tibor", "Attila", "Béla","Róbert", "Kálmán", "Albert", "Balázs", "Sándor"};
	private Map<String, List<String>> clubPlayers = new HashMap<>();
	
	public void fillTestMemoryDB() throws InterruptedException, ExecutionException {
		CompetitionInfo competition = CompetitionInfo.builder().name("Kelet Magyarország").build();
		List<SortingRule> sortingRules = Arrays.asList(SortingRule.GAMES_WON, SortingRule.GOAL_DIFFERENCE,
				SortingRule.GOAL_SCORED, SortingRule.RESULTS_BETWEEN_TEAMS);
		CompetitionRuleSet competitionRules = CompetitionRuleSet.builder().numberOfMatches(30).numberOfTeams(15)
				.promotion(1).relegation(2).yellowCardLimit(5).sortingRules(sortingRules).build();
		Stage stage = Stage.builder(competition.getName()).build();
		String competitionId = competitionService.addCompetition(competition, competitionRules, stage).get().getEntityId();

		
		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").name("Vámosoroszi KSE").build();
		String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
		clubPlayers.put(vamosoroszId, new ArrayList<>());
		
		ClubInfo tarpa = ClubInfo.builder().fullName("Tarpa Sport Club").name("Tarpa SC").build();
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();
		clubPlayers.put(tarpaId, new ArrayList<>());
		
		ClubInfo tisztaberek = ClubInfo.builder().fullName("Tisztaberek Sport Egyesulet").name("Tisztaberek SE").build();
		String tisztaberekId = clubService.addClub(tisztaberek).get().getEntityId();
		clubPlayers.put(tisztaberekId, new ArrayList<>());
		
		ClubInfo szatmarcseke = ClubInfo.builder().fullName("Szatmarcseke Községi Sport Egyesület").name("Szatmarcseke KSE").build();
		String szatmarcsekeId = clubService.addClub(szatmarcseke).get().getEntityId();
		clubPlayers.put(szatmarcsekeId, new ArrayList<>());
		
		ClubInfo tyukod = ClubInfo.builder().fullName("Tyukod Footbal Club").name("Tyukod FC").build();
		String tyukodId = clubService.addClub(tyukod).get().getEntityId();
		clubPlayers.put(tyukodId, new ArrayList<>());
		
		ClubInfo csengersimaert = ClubInfo.builder().fullName("Csengersimaert Községi Sport Egyesület").name("Csengersimaert KSE").build();
		String csengersimaertId = clubService.addClub(csengersimaert).get().getEntityId();
		clubPlayers.put(csengersimaertId, new ArrayList<>());
		
		ClubInfo nyirmeggyes = ClubInfo.builder().fullName("Nyirmeggyes Sportklub").name("Nyirmeggyes SK").build();
		String nyirmeggyesId = clubService.addClub(nyirmeggyes).get().getEntityId();
		clubPlayers.put(nyirmeggyesId, new ArrayList<>());
		
		ClubInfo tiszakorod = ClubInfo.builder().fullName("Tiszakorod Sport Egyesulet").name("Tiszakorod SE").build();
		String tiszakorodId = clubService.addClub(tiszakorod).get().getEntityId();
		clubPlayers.put(tiszakorodId, new ArrayList<>());
		
		ClubInfo nabrad = ClubInfo.builder().fullName("Nabrad Sport Egyesulet").name("Nabrad SE").build();
		String nabradId = clubService.addClub(nabrad).get().getEntityId();
		clubPlayers.put(nabradId, new ArrayList<>());
		
		ClubInfo Beregdaroc = ClubInfo.builder().fullName("Beregdaroc Sport Egyesulet").name("Beregdaroc SE").build();
		String beregdarocId = clubService.addClub(Beregdaroc).get().getEntityId();
		clubPlayers.put(beregdarocId, new ArrayList<>());
		
		ClubInfo csenger = ClubInfo.builder().fullName("Csenger Footbal Club").name("Csenger FC").build();
		String csengerId = clubService.addClub(csenger).get().getEntityId();
		clubPlayers.put(csengerId, new ArrayList<>());
		
		ClubInfo kolcse = ClubInfo.builder().fullName("Kolcse Sport Egyesulet").name("Kolcse SE").build();
		String kolcseId = clubService.addClub(kolcse).get().getEntityId();
		clubPlayers.put(kolcseId, new ArrayList<>());
		
		ClubInfo nagydobosi = ClubInfo.builder().fullName("Nagydobosi Labdarugo Sport Egyesulet").name("Nagydobosi LSE").build();
		String nagydobosiId = clubService.addClub(nagydobosi).get().getEntityId();
		clubPlayers.put(nagydobosiId, new ArrayList<>());
		
		ClubInfo milota = ClubInfo.builder().fullName("Milota Sport Egyesulet").name("Milota SE").build();
		String milotaId = clubService.addClub(milota).get().getEntityId();
		clubPlayers.put(milotaId, new ArrayList<>());
		
		competitionService.registerClubToCompetition(competitionId, clubPlayers.keySet().toArray(new String[clubPlayers.keySet().size()])).get();
		
		transferPlayers(vamosoroszId);
		transferPlayers(tarpaId);
		transferPlayers(tisztaberekId);
		transferPlayers(szatmarcsekeId);
		transferPlayers(tyukodId);
		transferPlayers(csengersimaertId);
		transferPlayers(nyirmeggyesId);
		transferPlayers(tiszakorodId);
		transferPlayers(nabradId);
		transferPlayers(beregdarocId);
		transferPlayers(csengerId);
		transferPlayers(kolcseId);
		transferPlayers(nagydobosiId);
		transferPlayers(milotaId);

		
		List<String> clubIds = new ArrayList<>(clubPlayers.keySet());
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
		
		fillPreMatches(matchWithIds);
		
		fillTurnWithRandomEvents(matchWithIds);

	}

	private void fillPreMatches(List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds) throws InterruptedException, ExecutionException {
		for (EntityWithIdAndVersion<MatchAggregator> matchWithId : matchWithIds) {
			List<LineUp> homeLineUps = new ArrayList<>();
			List<LineUp> awayLineUps = new ArrayList<>();
			MatchInfo matchInfo = matchWithId.getAggregate().getMatchInfo();
			
			List<String> homePlayers = clubPlayers.get(matchInfo.getHomeClubId());
			List<String> awayPlayers = clubPlayers.get(matchInfo.getAwayClubId());
			
			for (String homePlayer : homePlayers) {
				int shirtNumber = new Random().nextInt(99);
				if(homePlayers.indexOf(homePlayer) >= 11) {
					homeLineUps.add(LineUp.substitution(homePlayer, shirtNumber).build());
					continue;
				}
				homeLineUps.add(LineUp.starter(homePlayer, shirtNumber).build());
			}
			
			for (String awayPlayer : awayPlayers) {
				int shirtNumber = new Random().nextInt(99);
				if(awayPlayers.indexOf(awayPlayer) >= 11) {
					awayLineUps.add(LineUp.substitution(awayPlayer, shirtNumber).build());
					continue;
				}
				awayLineUps.add(LineUp.starter(awayPlayer, shirtNumber).build());
			}
			
			matchService.preMatch(matchWithId.getEntityId(), homeLineUps, awayLineUps).get();
		}
	}

	private void fillTurnWithRandomEvents(List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds) throws InterruptedException, ExecutionException {
		for (EntityWithIdAndVersion<MatchAggregator> matchWithId : matchWithIds) {
			MatchInfo matchInfo = matchWithId.getAggregate().getMatchInfo();
			List<MatchEvent> homeEvents = new ArrayList<>(); 
			List<GoalEvent> randomGoalEventsHome = randomGoalEvents(matchInfo.getHomeClubId());
			List<CardEvent> randomYellowCardEventsHome = randomYellowCardEvents(matchInfo.getHomeClubId());
			List<CardEvent> randomRedCardEventsHome = randomRedCardEvents(matchInfo.getHomeClubId());
			List<SubstitutionEvent> randomSubstutitionEventsHome = randomSubstutitionEvents(matchInfo.getAwayClubId());
			
			homeEvents.addAll(randomGoalEventsHome);
			homeEvents.addAll(randomYellowCardEventsHome);
			homeEvents.addAll(randomRedCardEventsHome);
			homeEvents.addAll(randomSubstutitionEventsHome);
			
			List<MatchEvent> awayEvents = new ArrayList<>();
			List<GoalEvent> randomGoalEvents = randomGoalEvents(matchInfo.getAwayClubId());
			List<CardEvent> randomYellowCardEvents = randomYellowCardEvents(matchInfo.getAwayClubId());
			List<CardEvent> randomRedCardEvents = randomRedCardEvents(matchInfo.getAwayClubId());
			List<SubstitutionEvent> randomSubstutitionEvents = randomSubstutitionEvents(matchInfo.getAwayClubId());
			
			awayEvents.addAll(randomGoalEvents);
			awayEvents.addAll(randomYellowCardEvents);
			awayEvents.addAll(randomRedCardEvents);
			awayEvents.addAll(randomSubstutitionEvents);
			
			matchService.playMatch(matchWithId.getEntityId(), homeEvents, awayEvents).get();
		}
	}
	
	private List<GoalEvent> randomGoalEvents(String clubId) {
		int goalAmount = new Random().nextInt(4);
		List<GoalEvent> goals = new ArrayList<>();
		for (int i = 0; i < goalAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			goals.add(GoalEvent.goalOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return goals;
	}
	
	private List<CardEvent> randomYellowCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(4);
		List<CardEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.yellowCardOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return cards;
	}
	
	private List<CardEvent> randomRedCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(2);
		List<CardEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.redCardOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return cards;
	}
	
	private List<SubstitutionEvent> randomSubstutitionEvents(String clubId) {
		int eventAmount = new Random().nextInt(3);
		List<SubstitutionEvent> substitutionEvents = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerInIndex = new Random().nextInt(11);
			int playerOutIndex = new Random().nextInt(5) + 11;
			int minute = new Random().nextInt(90);
			substitutionEvents.add(SubstitutionEvent.substutitionOf(clubPlayers.get(clubId).get(playerInIndex), clubPlayers.get(clubId).get(playerOutIndex), minute));
		}
		return substitutionEvents;
	}
	
	private void transferPlayers(String clubId) throws ExecutionException, InterruptedException {
		for (int i = 0; i < 17; i++) {
			try {
				String randomPlayer = randomPlayer();
				Transfer transfer = Transfer.builder().transferDate(LocalDateTime.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(randomPlayer).build();
				transferService.transferPlayer(transfer);
				putMap(clubId,randomPlayer);
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void putMap(String clubId, String randomPlayer) {
		List<String> players = clubPlayers.get(clubId);
		if(players == null) {
			players = new ArrayList<>();
			clubPlayers.put(clubId, players);
		}
		players.add(randomPlayer);
	}

	private String randomPlayer() throws InterruptedException, ExecutionException {
		int firstNameIndex = new Random().nextInt(firstNames.length-1);
		int secondNameIndex = new Random().nextInt(secondNames.length-1);
		
		Random random = new Random();
		int minDay = (int) LocalDate.of(1976, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
		
		return playerService.addPlayer(Player.builder().name(String.format("%s %s", firstNames[firstNameIndex], secondNames[secondNameIndex]))
				.birthday(randomBirthDate.format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
	}

}
