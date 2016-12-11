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
import com.mennyei.core.competition.domain.rule.CompetitionRules;
import com.mennyei.core.competition.domain.rule.SortingRule;
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
		List<SortingRule> rules = Arrays.asList(SortingRule.GAMES_WON,SortingRule.GOAL_DIFFERENCE,SortingRule.GOAL_SCORED,SortingRule.RESULTS_BETWEEN_TEAMS);
		CompetitionRules competitionRules = CompetitionRules.builder().numberOfMatches(30).numberOfTeams(15).promotion(1).relegation(2).yellowCardLimit(5).sortingRules(rules).build();
		String competitionId = competitionService.addCompetition(competition, competitionRules).get().getEntityId();

		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").shortName("VKSE").build();
		String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
		ClubInfo tarpa = ClubInfo.builder().fullName("Tarpa Sport Club").shortName("TSC").build();
		String tarpaId = clubService.addClub(tarpa).get().getEntityId();

		competitionService.registerClubToCompetition(competitionId, vamosoroszId, tarpaId).get();

		String playerId = playerService.addPlayer(Player.builder().name("Hajdu László").number(10)
				.birthday(LocalDate.of(1990, 1, 9).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player2Id = playerService.addPlayer(Player.builder().name("Kiss Pista").number(11)
				.birthday(LocalDate.of(1988, 11, 19).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(2)
				.birthday(LocalDate.of(1995, 3, 7).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player4Id = playerService.addPlayer(Player.builder().name("Szilágyi László").number(9)
				.birthday(LocalDate.of(1990, 1, 15).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player5Id = playerService.addPlayer(Player.builder().name("Kádár László").number(4)
				.birthday(LocalDate.of(1980, 12, 20).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player6Id = playerService.addPlayer(Player.builder().name("Szaniszló Ádám").number(1)
				.birthday(LocalDate.of(1991, 5, 14).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player7Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(7)
				.birthday(LocalDate.of(1985, 6, 4).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player8Id = playerService.addPlayer(Player.builder().name("Pintér Ádám").number(1)
				.birthday(LocalDate.of(1975, 12, 21).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player9Id = playerService.addPlayer(Player.builder().name("Sebestyén Zoltán").number(1)
				.birthday(LocalDate.of(1990, 5, 30).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player10Id = playerService.addPlayer(Player.builder().name("Bihari Tamás").number(1)
				.birthday(LocalDate.of(1986, 3, 5).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player11Id = playerService.addPlayer(Player.builder().name("Szabó János").number(1)
				.birthday(LocalDate.of(1987, 8, 1).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();

		extracted(vamosoroszId, playerId, player2Id, player3Id, player4Id, player5Id, player6Id, player7Id, player8Id, player9Id, player10Id, player11Id);

	}

	private void extracted(String clubId, String... playerIds) throws ExecutionException, InterruptedException {
		Arrays.stream(playerIds).forEach(p -> {
			try {
				transferService.transferPlayer(Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(p).build());
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
