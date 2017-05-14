package com.sp.organizer.backend.util;

import com.sp.core.backend.DateUtil;
import com.sp.core.backend.value.player.Player;
import com.sp.core.backend.value.player.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import player.service.PlayerService;
import transfer.service.TransferService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FillPlayerDatabase {
	
	@Autowired
	private TransferService transferService;

	@Autowired
	private PlayerService playerService;
	
	private String[] firstNames = {"Hajdu", "Kiss", "Nagy", "Szilágyi", "Talpas", "Gera", "Szabó", "Bihari", "Sebestyén", "Pintér", "Kádár", "Jelinek", "Szőllősi", "Gittinger", "Galina", "Bócsi"};
	private String[] secondNames = {"László", "István", "Zoltán", "Tamás", "Ádám", "János", "Gergő", "Szilárd", "Tibor", "Attila", "Béla","Róbert", "Kálmán", "Albert", "Balázs", "Sándor"};
	private Map<String, List<String>> clubPlayers = new HashMap<>();
	
	public void fillTestMemoryDB() throws InterruptedException, ExecutionException {
		
//		transferPlayers(vamosoroszId);
//		transferPlayers(tarpaId);
//		transferPlayers(tisztaberekId);
//		transferPlayers(szatmarcsekeId);
//		transferPlayers(tyukodId);
//		transferPlayers(csengersimaertId);
//		transferPlayers(nyirmeggyesId);
//		transferPlayers(tiszakorodId);
//		transferPlayers(nabradId);
//		transferPlayers(beregdarocId);
//		transferPlayers(csengerId);
//		transferPlayers(kolcseId);
//		transferPlayers(nagydobosiId);
//		transferPlayers(milotaId);
	}

//    private void fillPreMatches(List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds) throws InterruptedException, ExecutionException {
//        for (EntityWithIdAndVersion<MatchAggregator> matchWithId : matchWithIds) {
//            List<LineUp> homeLineUps = new ArrayList<>();
//            List<LineUp> awayLineUps = new ArrayList<>();
//            MatchInfo matchInfo = matchWithId.getAggregate().getMatchInfo();
//
////			List<String> homePlayers = clubIds.get(matchInfo.getHomeClubId());
////			List<String> awayPlayers = clubIds.get(matchInfo.getAwayClubId());
//
////			for (String homePlayer : homePlayers) {
////				int shirtNumber = new Random().nextInt(99);
////				if(homePlayers.indexOf(homePlayer) >= 11) {
////					homeLineUps.add(LineUp.substitution(homePlayer, shirtNumber).build());
////					continue;
////				}
////				homeLineUps.add(LineUp.starter(homePlayer, shirtNumber).build());
////			}
////
////			for (String awayPlayer : awayPlayers) {
////				int shirtNumber = new Random().nextInt(99);
////				if(awayPlayers.indexOf(awayPlayer) >= 11) {
////					awayLineUps.add(LineUp.substitution(awayPlayer, shirtNumber).build());
////					continue;
////				}
////				awayLineUps.add(LineUp.starter(awayPlayer, shirtNumber).build());
////			}
//
//            matchService.preMatch(matchWithId.getEntityId(), homeLineUps, awayLineUps).get();
//        }
//    }


	private void transferPlayers(String clubId) throws ExecutionException, InterruptedException {
		for (int i = 0; i < 17; i++) {
			try {
				String randomPlayer = randomPlayer();
				Transfer transfer = new Transfer(LocalDateTime.now().format(DateUtil.dateTimeFormatter),clubId, "", randomPlayer);
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

		return playerService.addPlayer(new Player(String.format("%s %s", firstNames[firstNameIndex], secondNames[secondNameIndex]), 0,
				randomBirthDate.format(DateUtil.dateTimeFormatterShort),"Magyar"))
				.get().getEntityId();
	}

}
