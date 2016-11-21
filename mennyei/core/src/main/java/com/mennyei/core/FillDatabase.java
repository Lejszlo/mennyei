package com.mennyei.core;

import com.mennyei.core.competition.domain.Competition;
import com.mennyei.core.competition.service.CompetitionService;
import com.mennyei.core.team.domain.Club;
import com.mennyei.core.team.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FillDatabase {
	
	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private ClubService clubService;
	
	public void fillTestMemoryDB() throws InterruptedException, ExecutionException {
		Competition competition = Competition.builder().name("Kelet Magyarország").build();
		String competitionId = competitionService.addCompetition(competition).get().getEntityId();
		
		
		Club vamosoroszi = Club.builder().fullName("Vámosoroszi").shortName("VKSE").build();
		String clubId = clubService.addClub(vamosoroszi).get().getEntityId();
		
		competitionService.registerClubToCompetition(competitionId, clubId).get();
		
	}
	
}
