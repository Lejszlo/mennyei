package com.mennyei.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.service.ClubService;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
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
	
	public void fillTestMemoryDB() throws InterruptedException, ExecutionException {
		CompetitionInfo competition = CompetitionInfo.builder().name("Kelet Magyarország").build();
		List<SortingRule> rules = Arrays.asList(SortingRule.GAMES_WON, SortingRule.GOAL_DIFFERENCE,
				SortingRule.GOAL_SCORED, SortingRule.RESULTS_BETWEEN_TEAMS);
		CompetitionRules competitionRules = CompetitionRules.builder().numberOfMatches(30).numberOfTeams(15)
				.promotion(1).relegation(2).yellowCardLimit(5).sortingRules(rules).build();
		Stage stage = Stage.builder(competition.getName()).build();
		String competitionId = competitionService.addCompetition(competition, competitionRules, stage).get().getEntityId();

		List<String> clubIds = new ArrayList<>();
		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").shortName("VKSE").build();
		String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
		clubIds.add(vamosoroszId);
		ClubInfo tarpa = ClubInfo.builder().fullName("Tarpa Sport Club").shortName("TSC").build();
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();
		clubIds.add(tarpaId);
		ClubInfo tisztaberek = ClubInfo.builder().fullName("Tisztaberek Sport Egyesulet").shortName("TSE").build();
		String tisztaberekId = clubService.addClub(tisztaberek).get().getEntityId();
		clubIds.add(tisztaberekId);
		ClubInfo szatmarcseke = ClubInfo.builder().fullName("Szatmarcseke Községi Sport Egyesület").shortName("SZKSE").build();
		String szatmarcsekeId = clubService.addClub(szatmarcseke).get().getEntityId();
		clubIds.add(szatmarcsekeId);
		ClubInfo tyukod = ClubInfo.builder().fullName("Tyukod Footbal Club").shortName("TFC").build();
		String tyukodId = clubService.addClub(tyukod).get().getEntityId();
		clubIds.add(tyukodId);
		ClubInfo csengersimaert = ClubInfo.builder().fullName("Csengersimaert Községi Sport Egyesület").shortName("CSKSE").build();
		String csengersimaertId = clubService.addClub(csengersimaert).get().getEntityId();
		clubIds.add(csengersimaertId);
		ClubInfo nyirmeggyes = ClubInfo.builder().fullName("Nyirmeggyes Sportklub").shortName("NYS").build();
		String nyirmeggyesId = clubService.addClub(nyirmeggyes).get().getEntityId();
		clubIds.add(nyirmeggyesId);
		ClubInfo tiszakorod = ClubInfo.builder().fullName("Tiszakorod Sport Egyesulet").shortName("TSE").build();
		String tiszakorodId = clubService.addClub(tiszakorod).get().getEntityId();
		clubIds.add(tiszakorodId);
		ClubInfo nabrad = ClubInfo.builder().fullName("Nabrad Sport Egyesulet").shortName("NSE").build();
		String nabradId = clubService.addClub(nabrad).get().getEntityId();
		clubIds.add(nabradId);
		ClubInfo Beregdaroc = ClubInfo.builder().fullName("Beregdaroc Sport Egyesulet").shortName("BSE").build();
		String beregdarocId = clubService.addClub(Beregdaroc).get().getEntityId();
		clubIds.add(beregdarocId);
		ClubInfo csenger = ClubInfo.builder().fullName("Csenger Footbal Club").shortName("CSFC").build();
		String csengerId = clubService.addClub(csenger).get().getEntityId();
		clubIds.add(csengerId);
		ClubInfo kolcse = ClubInfo.builder().fullName("Kolcse Sport Egyesulet").shortName("KSE").build();
		String kolcseId = clubService.addClub(kolcse).get().getEntityId();
		clubIds.add(kolcseId);
		ClubInfo nagydobosi = ClubInfo.builder().fullName("Nagydobosi Labdarugo Sport Egyesulet").shortName("NLSE").build();
		String nagydobosiId = clubService.addClub(nagydobosi).get().getEntityId();
		clubIds.add(nagydobosiId);
		ClubInfo milota = ClubInfo.builder().fullName("Milota Sport Egyesulet").shortName("MSE").build();
		String milotaId = clubService.addClub(milota).get().getEntityId();
		clubIds.add(milotaId);

		competitionService.registerClubToCompetition(competitionId, clubIds.toArray(new String[clubIds.size()])).get();
		
		
		for(int i=0; i< clubIds.size() - 1; ++i) {
			List<String> firstHalfClubIds = clubIds.subList(0, clubIds.size() / 2);
			List<String> seacondHalfClubIds = clubIds.subList(clubIds.size() / 2, clubIds.size());
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
				Match match = Match.builder(homeClubId, awayClubId, LocalDateTime.of(2017, 1, 9, 16, 00).plusDays(i * 7).format(DateUtil.dateTimeFormatter)).build();
				turn.getMatches().add(match);
			}
			
			competitionService.addMatch(competitionId, competition.getName(), turn).get();
			
			clubIds.add(1, clubIds.get(clubIds.size()-1));
			clubIds.remove(clubIds.size()-1);
		}
		
		
		String playerId = playerService.addPlayer(Player.builder().name("Hajdu László").number(10)
				.birthday(LocalDate.of(1990, 1, 9).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player2Id = playerService.addPlayer(Player.builder().name("Kiss Pista").number(11)
				.birthday(LocalDate.of(1988, 11, 19).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(2)
				.birthday(LocalDate.of(1995, 3, 7).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player4Id = playerService.addPlayer(Player.builder().name("Szilágyi László").number(9)
				.birthday(LocalDate.of(1990, 1, 15).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player5Id = playerService.addPlayer(Player.builder().name("Kádár László").number(4)
				.birthday(LocalDate.of(1980, 12, 20).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player6Id = playerService.addPlayer(Player.builder().name("Szaniszló Ádám").number(1)
				.birthday(LocalDate.of(1991, 5, 14).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player7Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(7)
				.birthday(LocalDate.of(1985, 6, 4).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player8Id = playerService.addPlayer(Player.builder().name("Pintér Ádám").number(5)
				.birthday(LocalDate.of(1975, 12, 21).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player9Id = playerService.addPlayer(Player.builder().name("Sebestyén Zoltán").number(3)
				.birthday(LocalDate.of(1990, 5, 30).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player10Id = playerService.addPlayer(Player.builder().name("Bihari Tamás").number(99)
				.birthday(LocalDate.of(1986, 3, 5).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();
		String player11Id = playerService.addPlayer(Player.builder().name("Szabó János").number(88)
				.birthday(LocalDate.of(1987, 8, 1).format(DateUtil.dateTimeFormatterShort)).nationality("Magyar").build())
				.get().getEntityId();

		transferPlayers(vamosoroszId, playerId, player2Id, player3Id, player4Id, player5Id, player6Id, player7Id, player8Id,
				player9Id, player10Id, player11Id);
//		extracted(tarpaId, playerId, player2Id, player3Id, player4Id, player5Id, player6Id, player7Id, player8Id,
//				player9Id, player10Id, player11Id);
		
//		Match match = Match.builder(vamosoroszId, tarpaId, DateUtil.ofDate(2017, 3, 12)).build();
//		competitionService.addMatch(competitionId, competition.getName(), 0, Arrays.asList(match)).get();

//		List<MatchEvent> events = Arrays.asList(GoalEvent.goalOf(playerId, 25), GoalEvent.goalOf(playerId, 63),
//				CardEvent.redCardOf(player2Id, 89), CardEvent.yellowCardOf(player11Id, 23),
//				CardEvent.yellowCardOf(player8Id, 53));
//		competitionService.playMatch(competitionId, competition.getName(), 0, vamosoroszId, events).get();

	}

	private void transferPlayers(String clubId, String... playerIds) throws ExecutionException, InterruptedException {
		Arrays.stream(playerIds).forEach(p -> {
			try {
				Transfer transfer = Transfer.builder().transferDate(LocalDateTime.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(p).build();
				transferService.transferPlayer(transfer);
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
