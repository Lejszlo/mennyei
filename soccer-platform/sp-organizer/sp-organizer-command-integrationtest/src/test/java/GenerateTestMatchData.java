import com.sp.core.backend.web.resource.IdResource;
import com.sp.core.query.configurations.Interval;
import com.sp.match.app.MatchApp;
import com.sp.match.backend.domain.MatchAggregator;
import com.sp.match.backend.service.MatchService;
import com.sp.organizer.command.aggregator.club.service.ClubService;
import com.sp.organizer.command.aggregator.competition.service.CompetitionService;
import com.sp.organizer.command.app.OrganizerCommandApp;
import com.sp.organizer.query.app.OrganizerQueryApp;
import command.competition.SaveCompetitionCommand;
import io.eventuate.EntityWithIdAndVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sp.match.api.value.MatchInfo;
import sp.match.api.value.event.CardEvent;
import sp.match.api.value.event.GoalEvent;
import sp.match.api.value.event.MatchEvent;
import value.club.ClubInfo;
import value.competition.CompetitionInfo;
import value.competition.rule.SortingRule;
import value.competition.rule.StageRuleSet;
import value.competition.season.Stage;
import value.competition.season.Turn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {OrganizerCommandApp.class, MatchApp.class, OrganizerQueryApp.class})
@AutoConfigureMockMvc
public class GenerateTestMatchData {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private MatchService matchService;

    private String[] firstNames = {"Hajdu", "Kiss", "Nagy", "Szilágyi", "Talpas", "Gera", "Szabó", "Bihari", "Sebestyén", "Pintér", "Kádár", "Jelinek", "Szőllősi", "Gittinger", "Galina", "Bócsi"};
    private String[] secondNames = {"László", "István", "Zoltán", "Tamás", "Ádám", "János", "Gergő", "Szilárd", "Tibor", "Attila", "Béla","Róbert", "Kálmán", "Albert", "Balázs", "Sándor"};
    private Set<String> clubIds = new HashSet<>();

    @Test
    public void generate() throws InterruptedException, ExecutionException {
        CompetitionInfo competition = new CompetitionInfo("Kelet Magyarország", Interval.from(LocalDate.of(2017, 8, 1), LocalDate.of(2018, 5, 31)));
        List<SortingRule> sortingRules = Arrays.asList(SortingRule.GAMES_WON, SortingRule.GOAL_DIFFERENCE,
                SortingRule.GOAL_SCORED, SortingRule.RESULTS_BETWEEN_TEAMS);
        StageRuleSet stageRuleSet = StageRuleSet.builder().numberOfMatches(30).numberOfTeams(15)
                .promotion(1).relegation(2).yellowCardLimit(5).sortingRules(sortingRules).build();
        SaveCompetitionCommand saveCompetitionCommand = SaveCompetitionCommand.builder()
                .competitionInfo(competition)
                .build();
        IdResource competitionId = competitionService.save(saveCompetitionCommand);

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

        Stage stage = Stage.builder(competition.getName(), 0)
                .interval(competition.getInterval())
                .stageRuleSet(stageRuleSet)
                .clubIds(clubIds)
                .build();

        List<Turn> turns = createTurns(stage, competitionId, Lists.from(clubIds.iterator()));

        stage.getTurns().addAll(turns);

        competitionService.addStage(stage, competitionId);
//
//		 fillTurnWithRandomEvents(matchWithIds, competitionId);

    }

    private List<Turn> createTurns(Stage stage, IdResource competitionId, List<String> clubIds) throws InterruptedException, ExecutionException {
        List<Turn> turns = new ArrayList<>();
        List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds = new ArrayList<>();
        LocalDateTime startLocalDateTime = LocalDateTime.of(2017, 1, 9, 16, 0);
        LocalDateTime endLocalDateTime = null;
        for(int i=0; i< clubIds.size() - 1; ++i) {
            List<String> firstHalfClubIds = new ArrayList<>(clubIds.subList(0, clubIds.size() / 2));
            List<String> seacondHalfClubIds = new ArrayList<>(clubIds.subList(clubIds.size() / 2, clubIds.size()));
            Collections.reverse(seacondHalfClubIds);
            Turn.TurnBuilder turnBuilder = Turn.builder(i+1);
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
                endLocalDateTime = startLocalDateTime.plusWeeks(i);
                MatchInfo matchInfo = MatchInfo.builder(homeClubId, awayClubId, endLocalDateTime).build();
                EntityWithIdAndVersion<MatchAggregator> matchWithId = matchService.addMatch(matchInfo).get();
                matchWithIds.add(matchWithId);
                matchInfos.add(matchInfo);
                turnBuilder.match(matchWithId.getEntityId());
            }

            turnBuilder.interval(Interval.from(startLocalDateTime.toLocalDate(), endLocalDateTime.toLocalDate()));
            Turn turn = turnBuilder.build();
            turns.add(turn);

            Turn.TurnBuilder reTurnBuilder = Turn.builder(turn.getIndex() + (clubIds.size() - 1));
            new ArrayList<>(matchInfos).forEach(match -> {
                LocalDateTime plusMonths = match.getMatchDate().plusMonths(3);
                MatchInfo matchInfo = MatchInfo.builder(match.getAwayClubId(), match.getHomeClubId(), plusMonths).build();
                try {
                    EntityWithIdAndVersion<MatchAggregator> matchWithId = matchService.addMatch(matchInfo).get();
                    matchWithIds.add(matchWithId);
                    reTurnBuilder.match(matchWithId.getEntityId());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            turns.add(reTurnBuilder.build());

            clubIds.add(1, clubIds.get(clubIds.size()-1));
            clubIds.remove(clubIds.size()-1);
        }

        return turns;
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
