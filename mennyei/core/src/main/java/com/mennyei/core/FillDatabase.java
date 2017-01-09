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
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.match.domain.lineup.LineUp;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.card.CardEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.goal.GoalEvent;
import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.core.competition.domain.rule.SortingRule;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.service.CompetitionService;
import com.mennyei.core.player.domain.Player;
import com.mennyei.core.player.service.PlayerService;
import com.mennyei.core.transfer.domain.Transfer;
import com.mennyei.core.transfer.service.TransferService;

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
		transferPlayers(vamosoroszId);
		
		ClubInfo tarpa = ClubInfo.builder().fullName("Tarpa Sport Club").name("Tarpa SC").build();
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();
		clubPlayers.put(tarpaId, new ArrayList<>());
		transferPlayers(tarpaId);
		
		ClubInfo tisztaberek = ClubInfo.builder().fullName("Tisztaberek Sport Egyesulet").name("Tisztaberek SE").build();
		String tisztaberekId = clubService.addClub(tisztaberek).get().getEntityId();
		clubPlayers.put(tisztaberekId, new ArrayList<>());
		transferPlayers(tisztaberekId);
		
		ClubInfo szatmarcseke = ClubInfo.builder().fullName("Szatmarcseke Községi Sport Egyesület").name("Szatmarcseke KSE").build();
		String szatmarcsekeId = clubService.addClub(szatmarcseke).get().getEntityId();
		clubPlayers.put(szatmarcsekeId, new ArrayList<>());
		transferPlayers(szatmarcsekeId);
		
		ClubInfo tyukod = ClubInfo.builder().fullName("Tyukod Footbal Club").name("Tyukod FC").build();
		String tyukodId = clubService.addClub(tyukod).get().getEntityId();
		clubPlayers.put(tyukodId, new ArrayList<>());
		transferPlayers(tyukodId);
		
		ClubInfo csengersimaert = ClubInfo.builder().fullName("Csengersimaert Községi Sport Egyesület").name("Csengersimaert KSE").build();
		String csengersimaertId = clubService.addClub(csengersimaert).get().getEntityId();
		clubPlayers.put(csengersimaertId, new ArrayList<>());
		transferPlayers(csengersimaertId);
		
		ClubInfo nyirmeggyes = ClubInfo.builder().fullName("Nyirmeggyes Sportklub").name("Nyirmeggyes SK").build();
		String nyirmeggyesId = clubService.addClub(nyirmeggyes).get().getEntityId();
		clubPlayers.put(nyirmeggyesId, new ArrayList<>());
		transferPlayers(nyirmeggyesId);
		
		ClubInfo tiszakorod = ClubInfo.builder().fullName("Tiszakorod Sport Egyesulet").name("Tiszakorod SE").build();
		String tiszakorodId = clubService.addClub(tiszakorod).get().getEntityId();
		clubPlayers.put(tiszakorodId, new ArrayList<>());
		transferPlayers(tiszakorodId);
		
		ClubInfo nabrad = ClubInfo.builder().fullName("Nabrad Sport Egyesulet").name("Nabrad SE").build();
		String nabradId = clubService.addClub(nabrad).get().getEntityId();
		clubPlayers.put(nabradId, new ArrayList<>());
		transferPlayers(nabradId);
		
		ClubInfo Beregdaroc = ClubInfo.builder().fullName("Beregdaroc Sport Egyesulet").name("Beregdaroc SE").build();
		String beregdarocId = clubService.addClub(Beregdaroc).get().getEntityId();
		clubPlayers.put(beregdarocId, new ArrayList<>());
		transferPlayers(beregdarocId);
		
		ClubInfo csenger = ClubInfo.builder().fullName("Csenger Footbal Club").name("Csenger FC").build();
		String csengerId = clubService.addClub(csenger).get().getEntityId();
		clubPlayers.put(csengerId, new ArrayList<>());
		transferPlayers(csengerId);
		
		ClubInfo kolcse = ClubInfo.builder().fullName("Kolcse Sport Egyesulet").name("Kolcse SE").build();
		String kolcseId = clubService.addClub(kolcse).get().getEntityId();
		clubPlayers.put(kolcseId, new ArrayList<>());
		transferPlayers(kolcseId);
		
		ClubInfo nagydobosi = ClubInfo.builder().fullName("Nagydobosi Labdarugo Sport Egyesulet").name("Nagydobosi LSE").build();
		String nagydobosiId = clubService.addClub(nagydobosi).get().getEntityId();
		clubPlayers.put(nagydobosiId, new ArrayList<>());
		transferPlayers(nagydobosiId);
		
		ClubInfo milota = ClubInfo.builder().fullName("Milota Sport Egyesulet").name("Milota SE").build();
		String milotaId = clubService.addClub(milota).get().getEntityId();
		clubPlayers.put(milotaId, new ArrayList<>());
		transferPlayers(milotaId);

		competitionService.registerClubToCompetition(competitionId, clubPlayers.keySet().toArray(new String[clubPlayers.keySet().size()])).get();
		
		List<String> clubIds = new ArrayList<>(clubPlayers.keySet());
		for(int i=0; i< clubIds.size() - 1; ++i) {
			List<String> firstHalfClubIds = new ArrayList<>(clubIds.subList(0, clubIds.size() / 2));
			List<String> seacondHalfClubIds = new ArrayList<>(clubIds.subList(clubIds.size() / 2, clubIds.size()));
			Collections.reverse(seacondHalfClubIds);
			Turn turn = Turn.builder(i+1).build();
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
				Match match = Match.builder(homeClubId, awayClubId, LocalDateTime.of(2017, 1, 9, 16, 00).plusWeeks(i).format(DateUtil.dateTimeFormatter)).build();
				turn.getMatches().add(match);
			}
			competitionService.addTurn(competitionId, competition.getName(), turn).get();
			
			fillTurnWithRandomEvents(competition, competitionId, turn);
			
			fillPreMatches(competition, competitionId, turn);
			
			Turn reTurn = Turn.builder(turn.getIndex() + (clubIds.size() - 1)).build();
			turn.getMatches().stream().forEach(m -> {
				LocalDateTime plusMonths = LocalDateTime.parse(m.getMatchDate(), DateUtil.dateTimeFormatter).plusMonths(3);
				Match match = Match.builder(m.getAwayClubId(), m.getHomeClubId(), plusMonths.format(DateUtil.dateTimeFormatter)).build();
				reTurn.getMatches().add(match);
			});
			competitionService.addTurn(competitionId, competition.getName(), reTurn).get();
			
			clubIds.add(1, clubIds.get(clubIds.size()-1));
			clubIds.remove(clubIds.size()-1);
		}

	}

	private void fillPreMatches(CompetitionInfo competition, String competitionId, Turn turn) {
		for (Match match : turn.getMatches()) {
			List<String> homePlayers = clubPlayers.get(match.getHomeClubId());
			List<String> awayPlayers = clubPlayers.get(match.getAwayClubId());
			
			for (String homePlayer : homePlayers) {
				int shirtNumber = new Random().nextInt(99);
				LineUp lineUp;
				if(homePlayers.indexOf(homePlayer) >= 11) {
					lineUp = LineUp.substitution(homePlayer, shirtNumber).build();
					continue;
				}
				lineUp = LineUp.starter(homePlayer, shirtNumber).build();
				match.getHomeLineUps().add(lineUp);
			}
			
			for (String awayPlayer : awayPlayers) {
				int shirtNumber = new Random().nextInt(99);
				LineUp lineUp;
				if(homePlayers.indexOf(awayPlayer) >= 11) {
					lineUp = LineUp.substitution(awayPlayer, shirtNumber).build();
					continue;
				}
				lineUp = LineUp.starter(awayPlayer, shirtNumber).build();
				match.getAwayLineUps().add(lineUp);
			}

		}
	}

	private void fillTurnWithRandomEvents(CompetitionInfo competition, String competitionId, Turn turn) throws InterruptedException, ExecutionException {
		for (Match match : turn.getMatches()) {
			List<MatchEvent> homeEvents = randomGoalEvents(match.getHomeClubId());
			homeEvents.addAll(randomYellowCardEvents(match.getHomeClubId()));
			homeEvents.addAll(randomRedCardEvents(match.getHomeClubId()));
			
			List<MatchEvent> awayEvents = randomGoalEvents(match.getAwayClubId());
			awayEvents.addAll(randomYellowCardEvents(match.getAwayClubId()));
			awayEvents.addAll(randomRedCardEvents(match.getAwayClubId()));
			
			competitionService.playMatch(competitionId, competition.getName(), turn.getIndex(), match.getHomeClubId(), homeEvents, awayEvents).get();
		}
	}
	
	private List<MatchEvent> randomGoalEvents(String clubId) {
		int goalAmount = new Random().nextInt(4);
		List<MatchEvent> goals = new ArrayList<>();
		for (int i = 0; i < goalAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			goals.add(GoalEvent.goalOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return goals;
	}
	
	private List<MatchEvent> randomYellowCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(4);
		List<MatchEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.yellowCardOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return cards;
	}
	
	private List<MatchEvent> randomRedCardEvents(String clubId) {
		int eventAmount = new Random().nextInt(1);
		List<MatchEvent> cards = new ArrayList<>();
		for (int i = 0; i < eventAmount; i++) {
			int playerIndex = new Random().nextInt(16);
			int minute = new Random().nextInt(90);
			cards.add(CardEvent.redCardOf(clubPlayers.get(clubId).get(playerIndex), minute));
		}
		return cards;
	}
	
	private void transferPlayers(String clubId) throws ExecutionException, InterruptedException {
		for (int i = 0; i < 17; i++) {
			try {
				String randomPlayer = randomPlayer();
				Transfer transfer = Transfer.builder().transferDate(LocalDateTime.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(randomPlayer).build();
				transferService.transferPlayer(transfer);
				clubPlayers.get(clubId).add(randomPlayer);
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
