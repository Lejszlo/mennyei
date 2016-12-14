package com.mennyei.core;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.service.ClubService;
import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.match.domain.match.event.MatchEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.card.CardEvent;
import com.mennyei.core.competition.domain.match.domain.match.event.goal.GoalEvent;
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.rule.SortingRule;
import com.mennyei.core.competition.domain.season.Stage;
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
		System.out.println("competitionId " + competitionId);

		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").shortName("VKSE").build();
		String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
		System.out.println("vamosoroszId " + vamosoroszId);
		ClubInfo tarpa = ClubInfo.builder().fullName("Tarpa Sport Club").shortName("TSC").build();
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();
		System.out.println("tarpaId " + tarpaId);


		competitionService.registerClubToCompetition(competitionId, vamosoroszId, tarpaId).get();
		System.out.println("clubs registrated");

		String playerId = playerService.addPlayer(Player.builder().name("Hajdu László").number(10)
				.birthday(LocalDate.of(1990, 1, 9).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId " + playerId);
		String player2Id = playerService.addPlayer(Player.builder().name("Kiss Pista").number(11)
				.birthday(LocalDate.of(1988, 11, 19).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId2 " + player2Id);
		String player3Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(2)
				.birthday(LocalDate.of(1995, 3, 7).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId3 " + player3Id);
		String player4Id = playerService.addPlayer(Player.builder().name("Szilágyi László").number(9)
				.birthday(LocalDate.of(1990, 1, 15).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId4 " + player4Id);
		String player5Id = playerService.addPlayer(Player.builder().name("Kádár László").number(4)
				.birthday(LocalDate.of(1980, 12, 20).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId5 " + player5Id);
		String player6Id = playerService.addPlayer(Player.builder().name("Szaniszló Ádám").number(1)
				.birthday(LocalDate.of(1991, 5, 14).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId6 " + player6Id);
		String player7Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(7)
				.birthday(LocalDate.of(1985, 6, 4).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId7 " + player7Id);
		String player8Id = playerService.addPlayer(Player.builder().name("Pintér Ádám").number(5)
				.birthday(LocalDate.of(1975, 12, 21).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId8 " + player8Id);
		String player9Id = playerService.addPlayer(Player.builder().name("Sebestyén Zoltán").number(3)
				.birthday(LocalDate.of(1990, 5, 30).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId9 " + player9Id);
		String player10Id = playerService.addPlayer(Player.builder().name("Bihari Tamás").number(99)
				.birthday(LocalDate.of(1986, 3, 5).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId10 " + player10Id);
		String player11Id = playerService.addPlayer(Player.builder().name("Szabó János").number(88)
				.birthday(LocalDate.of(1987, 8, 1).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		System.out.println("playerId11 " + player11Id);

		transferPlayers(vamosoroszId, playerId, player2Id, player3Id, player4Id, player5Id, player6Id, player7Id, player8Id,
				player9Id, player10Id, player11Id);
//		extracted(tarpaId, playerId, player2Id, player3Id, player4Id, player5Id, player6Id, player7Id, player8Id,
//				player9Id, player10Id, player11Id);
		
		Match match = Match.builder(vamosoroszId, tarpaId, DateUtil.ofDate(2017, 3, 12)).build();
		competitionService.addMatch(competitionId, competition.getName(), 0, Arrays.asList(match)).get();

		List<MatchEvent> events = Arrays.asList(GoalEvent.goalOf(playerId, 25), GoalEvent.goalOf(playerId, 63),
				CardEvent.redCardOf(player2Id, 89), CardEvent.yellowCardOf(player11Id, 23),
				CardEvent.yellowCardOf(player8Id, 53));
		competitionService.fillMatchWithEvents(competitionId, competition.getName(), 0, vamosoroszId, events).get();

	}

	private void transferPlayers(String clubId, String... playerIds) throws ExecutionException, InterruptedException {
		Arrays.stream(playerIds).forEach(p -> {
			try {
				Transfer transfer = Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(p).build();
				transferService.transferPlayer(transfer);
				System.out.println("player transfer" + transfer.getPlayerId());
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
