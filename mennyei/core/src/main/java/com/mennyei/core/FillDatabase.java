package com.mennyei.core;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.domain.value.ClubInfo;
import com.mennyei.core.club.service.ClubService;
import com.mennyei.core.competition.domain.CompetitionInfo;
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
		String competitionId = competitionService.addCompetition(competition).get().getEntityId();

		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").shortName("VKSE")
				.build();
		String clubId = clubService.addClub(vamosoroszi).get().getEntityId();

		competitionService.registerClubToCompetition(competitionId, clubId).get();

		String playerId = playerService.addPlayer(Player.builder().name("Hajdu László").number(10)
				.birthday(LocalDate.of(1990, 1, 9).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player2Id = playerService.addPlayer(Player.builder().name("Kiss Pista").number(11)
				.birthday(LocalDate.of(1988, 11, 19).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Gera Zoltán").number(2)
				.birthday(LocalDate.of(1995, 3, 7).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Szilágyi László").number(9)
				.birthday(LocalDate.of(1990, 1, 15).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Kádár László").number(11)
				.birthday(LocalDate.of(1980, 12, 20).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();
		String player3Id = playerService.addPlayer(Player.builder().name("Szaniszló Ádám").number(11)
				.birthday(LocalDate.of(1991, 5, 14).format(DateUtil.dateTimeFormatter)).nationality("Magyar").build())
				.get().getEntityId();

		transferService
				.transferPlayer(Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter))
						.targetTeamId(clubId).playerId(playerId).build());
		transferService
				.transferPlayer(Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter))
						.targetTeamId(clubId).playerId(player2Id).build());
		transferService
				.transferPlayer(Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter))
						.targetTeamId(clubId).playerId(player3Id).build());
	}

}
