package com.mennyei.core;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mennyei.core.club.domain.ClubInfo;
import com.mennyei.core.club.service.ClubService;
import com.mennyei.core.competition.domain.Competition;
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
		Competition competition = Competition.builder().name("Kelet Magyarország").build();
		String competitionId = competitionService.addCompetition(competition).get().getEntityId();
		
		ClubInfo vamosoroszi = ClubInfo.builder().fullName("Vámosoroszi Községi Sport Egyesület").shortName("VKSE").build();
		String clubId = clubService.addClub(vamosoroszi).get().getEntityId();
		
		competitionService.registerClubToCompetition(competitionId, clubId).get();

		String playerId = playerService.addPlayer(Player.builder().name("Hajdu László").number(10).birthday(LocalDate.of(1990,1,9).format(DateUtil.dateTimeFormatter)).build()).get().getEntityId();

		transferService.transferPlayer(Transfer.builder().transferDate(LocalDate.now().format(DateUtil.dateTimeFormatter)).targetTeamId(clubId).playerId(playerId).build()).get();
	}
	
}
