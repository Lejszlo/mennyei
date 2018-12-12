package sp.frame.integrationtest;

import com.sp.competition.command.aggregator.service.CompetitionService;
import com.sp.competition.command.aggregator.service.StageService;
import com.sp.core.query.configurations.Interval;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.api.value.event.CardEvent;
import com.sp.match.api.value.event.GameEvent;
import com.sp.match.api.value.event.GoalEvent;
import com.sp.match.command.aggregator.domain.MatchAggregator;
import com.sp.match.command.aggregator.service.MatchService;
import com.sp.match.command.app.MatchCommandApp;
import com.sp.match.query.app.MatchQueryApp;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.ClubId;
import com.sp.organizer.command.aggregator.club.service.ClubService;
import com.sp.organizer.command.app.OrganizerCommandApp;
import com.sp.organizer.query.app.OrganizerQueryApp;
import io.eventuate.EntityWithIdAndVersion;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sp.competition.api.value.season.Turn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.sp.organizer.api.value.club.AwayClubId.awayClubId;
import static com.sp.organizer.api.value.club.HomeClubId.homeClubId;
import static java.time.format.DateTimeFormatter.ofPattern;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {OrganizerCommandApp.class, OrganizerQueryApp.class, MatchCommandApp.class, MatchQueryApp.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GenerateTestMatchData {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private StageService stageService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private MatchService matchService;

    private String[] firstNames = {"Hajdu", "Kiss", "Nagy", "Szilágyi", "Talpas", "Gera", "Szabó", "Bihari", "Sebestyén", "Pintér", "Kádár", "Jelinek", "Szőllősi", "Gittinger", "Galina", "Bócsi"};
    private String[] secondNames = {"László", "István", "Zoltán", "Tamás", "Ádám", "János", "Gergő", "Szilárd", "Tibor", "Attila", "Béla", "Róbert", "Kálmán", "Albert", "Balázs", "Sándor"};
    private Set<String> clubIds = new HashSet<>();

    @After
    public void afterMethods() {
        System.out.println("End");
    }

    private void createTurns(String competitionId, List<String> clubIds, UUID stageId) throws InterruptedException, ExecutionException {
        List<Turn> turns = new ArrayList<>();
        List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds = new ArrayList<>();
        LocalDateTime startLocalDateTime = LocalDate.now().atTime(18, 0).minusMonths(5);
        LocalDateTime endLocalDateTime = null;
        for (int i = 0; i < clubIds.size() - 1; ++i) {
            List<String> firstHalfClubIds = new ArrayList<>(clubIds.subList(0, clubIds.size() / 2));
            List<String> seacondHalfClubIds = new ArrayList<>(clubIds.subList(clubIds.size() / 2, clubIds.size()));
            Collections.reverse(seacondHalfClubIds);
            Turn.TurnBuilder turnBuilder = Turn.builder(i + 1);
            List<MatchInfo> matchInfos = new ArrayList<>();
            for (int j = 0; j < firstHalfClubIds.size(); ++j) {
                HomeClubId homeClubId = homeClubId("");
                AwayClubId awayClubId = awayClubId("");
                if (i % 2 == 0) {
                    homeClubId = homeClubId(firstHalfClubIds.get(j));
                    awayClubId = awayClubId(seacondHalfClubIds.get(j));
                } else {
                    homeClubId = homeClubId(seacondHalfClubIds.get(j));
                    awayClubId = awayClubId(firstHalfClubIds.get(j));
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
                LocalDateTime plusMonths = LocalDateTime.parse(match.getMatchDate(), ofPattern("yyyy-MM-dd HH:mm:ss"));
                MatchInfo matchInfo = MatchInfo.builder(match.getHomeClubId(), match.getAwayClubId(), plusMonths).build();
                try {
                    EntityWithIdAndVersion<MatchAggregator> matchWithId = matchService.addMatch(matchInfo).get();
                    matchWithIds.add(matchWithId);
                    reTurnBuilder.match(matchWithId.getEntityId());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

            turns.add(reTurnBuilder.build());

            clubIds.add(1, clubIds.get(clubIds.size() - 1));
            clubIds.remove(clubIds.size() - 1);
        }

        stageService.addTurnsToStage(competitionId, stageId, turns).get();

//        fillTurnWithRandomEvents(matchWithIds, competitionId, stageId);
    }

    private void fillTurnWithRandomEvents(List<EntityWithIdAndVersion<MatchAggregator>> matchWithIds, String competitionId, UUID stageId) throws InterruptedException, ExecutionException {
        for (EntityWithIdAndVersion<MatchAggregator> matchWithId : matchWithIds) {
            MatchInfo matchInfo = matchWithId.getAggregate().getMatchInfo();
            List<GameEvent> homeEvents = new ArrayList<>();
            List<GoalEvent> randomGoalEventsHome = randomGoalEvents(matchInfo.getHomeClubId());
            List<CardEvent> randomYellowCardEventsHome = randomYellowCardEvents();
            List<CardEvent> randomRedCardEventsHome = randomRedCardEvents();

            homeEvents.addAll(randomGoalEventsHome);
            homeEvents.addAll(randomYellowCardEventsHome);
            homeEvents.addAll(randomRedCardEventsHome);

            List<GameEvent> awayEvents = new ArrayList<>();
            List<GoalEvent> randomGoalEvents = randomGoalEvents(matchInfo.getAwayClubId());
            List<CardEvent> randomYellowCardEvents = randomYellowCardEvents();
            List<CardEvent> randomRedCardEvents = randomRedCardEvents();

            awayEvents.addAll(randomGoalEvents);
            awayEvents.addAll(randomYellowCardEvents);
            awayEvents.addAll(randomRedCardEvents);

//            matchService.playMatch(matchInfo.getHomeClubId(),
//                    matchInfo.getAwayClubId(),
//                    competitionId(competitionId),
//                    StageId.stageId(SeasonId.seasonId(competitionId.), UUID.fromString(stageId.toString())),
//                    matchWithId.getEntityId(),
//                    homeEvents,
//                    awayEvents).get();
        }
    }

    private List<GoalEvent> randomGoalEvents(ClubId clubId) {
        int goalAmount = new Random().nextInt(4);
        List<GoalEvent> goals = new ArrayList<>();
        for (int i = 0; i < goalAmount; i++) {
            int minute = new Random().nextInt(90);
            goals.add(GoalEvent.goalOf(minute));
        }
        return goals;
    }

    private List<CardEvent> randomYellowCardEvents() {
        int eventAmount = new Random().nextInt(4);
        List<CardEvent> cards = new ArrayList<>();
        for (int i = 0; i < eventAmount; i++) {
            int minute = new Random().nextInt(90);
            cards.add(CardEvent.yellowCardOf(minute));
        }
        return cards;
    }

    private List<CardEvent> randomRedCardEvents() {
        int eventAmount = new Random().nextInt(2);
        List<CardEvent> cards = new ArrayList<>();
        for (int i = 0; i < eventAmount; i++) {
            int minute = new Random().nextInt(90);
            cards.add(CardEvent.redCardOf(minute));
        }
        return cards;
    }


}
