package sp.frame.integrationtest;

import com.sp.core.query.configurations.Interval;
import com.sp.match.api.value.event.GameEvent;
import com.sp.match.command.aggregator.domain.MatchAggregator;
import com.sp.match.command.aggregator.service.MatchService;
import com.sp.match.command.app.MatchCommandApp;
import com.sp.match.query.app.MatchQueryApp;
import com.sp.organizer.api.command.competition.AddStageCompetitionCommand;
import com.sp.organizer.api.value.competition.StageId;
import com.sp.organizer.command.aggregator.club.service.ClubService;
import com.sp.organizer.command.aggregator.competition.service.CompetitionService;
import com.sp.organizer.command.aggregator.competition.service.StageService;
import com.sp.organizer.command.app.OrganizerCommandApp;
import com.sp.organizer.query.app.OrganizerQueryApp;
import com.sp.organizer.api.command.competition.CreateCompetitionCommand;
import io.eventuate.EntityWithIdAndVersion;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.api.value.event.CardEvent;
import com.sp.match.api.value.event.GoalEvent;
import com.sp.organizer.api.value.club.AwayClubId;
import com.sp.organizer.api.value.club.ClubId;
import com.sp.organizer.api.value.club.ClubInfo;
import com.sp.organizer.api.value.club.HomeClubId;
import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.api.value.competition.rule.SortingRule;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import com.sp.organizer.api.value.competition.season.Turn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.sp.organizer.api.value.competition.CompetitionId.competitionId;
import static java.time.format.DateTimeFormatter.*;
import static com.sp.organizer.api.value.club.AwayClubId.awayClubId;
import static com.sp.organizer.api.value.club.HomeClubId.homeClubId;

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

    @Test
    public void generateCompetition() throws ExecutionException, InterruptedException {
        CompetitionInfo competition = CompetitionInfo.builder()
                .name("Nyugat Magyarország")
                .interval(Interval.from(LocalDate.now().minusMonths(5), LocalDate.now().plusMonths(5)))
                .build();

        List<SortingRule> sortingRules = Arrays.asList(SortingRule.GAMES_WON,
                SortingRule.GOAL_DIFFERENCE,
                SortingRule.GOAL_SCORED,
                SortingRule.RESULTS_BETWEEN_TEAMS);

        StageRuleSet stageRuleSet = StageRuleSet.builder()
                .numberOfMatches(26)
                .numberOfTeams(14)
                .promotion(2)
                .relegation(2)
                .yellowCardLimit(5)
                .sortingRules(sortingRules)
                .build();
        CreateCompetitionCommand createCompetitionCommand = CreateCompetitionCommand.builder()
                .competitionInfo(competition)
                .build();

        ClubInfo vamosoroszi = new ClubInfo("Vámosoroszi Községi Sport Egyesület", "Vámosoroszi KSE");
        String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
        clubIds.add(vamosoroszId);
    }

    @Test
    public void generate() throws InterruptedException, ExecutionException {
        CompetitionInfo competition = CompetitionInfo.builder().name("Kelet Magyarország")
                .interval(Interval.from(LocalDate.now().minusMonths(5), LocalDate.now().plusMonths(5)))
                .build();

        List<SortingRule> sortingRules = Arrays.asList(SortingRule.GAMES_WON,
                SortingRule.GOAL_DIFFERENCE,
                SortingRule.GOAL_SCORED,
                SortingRule.RESULTS_BETWEEN_TEAMS);

        StageRuleSet stageRuleSet = StageRuleSet.builder()
                .numberOfMatches(26)
                .numberOfTeams(14)
                .promotion(2)
                .relegation(2)
                .yellowCardLimit(5)
                .sortingRules(sortingRules)
                .build();
        CreateCompetitionCommand createCompetitionCommand = CreateCompetitionCommand.builder()
                .competitionInfo(competition)
                .build();
        String competitionId = competitionService.save(createCompetitionCommand).get().getEntityId();

        //ADD STAGE
        UUID stageId = UUID.randomUUID();
        competitionService.addStage(
                AddStageCompetitionCommand.builder()
                        .id(stageId)
                        .name(competition.getName())
                        .interval(competition.getInterval())
                        .stageRuleSet(stageRuleSet)
                        .build(),
                competitionId
        ).get();

        ClubInfo vamosoroszi = new ClubInfo("Vámosoroszi Községi Sport Egyesület", "Vámosoroszi KSE");
        String vamosoroszId = clubService.addClub(vamosoroszi).get().getEntityId();
        clubIds.add(vamosoroszId);

        ClubInfo tarpa = new ClubInfo("Tarpa Sport Club", "Tarpa SC");
        String tarpaId = clubService.addClub(tarpa).get().getEntityId();
        clubIds.add(tarpaId);

        ClubInfo tisztaberek = new ClubInfo("Tisztaberek Sport Egyesulet", "Tisztaberek SE");
        String tisztaberekId = clubService.addClub(tisztaberek).get().getEntityId();
        clubIds.add(tisztaberekId);

        ClubInfo szatmarcseke = new ClubInfo("Szatmarcseke Községi Sport Egyesület", "Szatmarcseke KSE");
        String szatmarcsekeId = clubService.addClub(szatmarcseke).get().getEntityId();
        clubIds.add(szatmarcsekeId);

        ClubInfo tyukod = new ClubInfo("Tyukod Footbal Club", "Tyukod FC");
        String tyukodId = clubService.addClub(tyukod).get().getEntityId();
        clubIds.add(tyukodId);

        ClubInfo csengersimaert = new ClubInfo("Csengersimaert Községi Sport Egyesület", "Csengersimaert KSE");
        String csengersimaertId = clubService.addClub(csengersimaert).get().getEntityId();
        clubIds.add(csengersimaertId);

        ClubInfo nyirmeggyes = new ClubInfo("Nyirmeggyes Sportklub", "Nyirmeggyes SK");
        String nyirmeggyesId = clubService.addClub(nyirmeggyes).get().getEntityId();
        clubIds.add(nyirmeggyesId);

        ClubInfo tiszakorod = new ClubInfo("Tiszakorod Sport Egyesulet", "Tiszakorod SE");
        String tiszakorodId = clubService.addClub(tiszakorod).get().getEntityId();
        clubIds.add(tiszakorodId);

        ClubInfo nabrad = new ClubInfo("Nabrad Sport Egyesulet", "Nabrad SE");
        String nabradId = clubService.addClub(nabrad).get().getEntityId();
        clubIds.add(nabradId);

        ClubInfo Beregdaroc = new ClubInfo("Beregdaroc Sport Egyesulet", "Beregdaroc SE");
        String beregdarocId = clubService.addClub(Beregdaroc).get().getEntityId();
        clubIds.add(beregdarocId);

        ClubInfo csenger = new ClubInfo("Csenger Footbal Club", "Csenger FC");
        String csengerId = clubService.addClub(csenger).get().getEntityId();
        clubIds.add(csengerId);

        ClubInfo kolcse = new ClubInfo("Kolcse Sport Egyesulet", "Kolcse SE");
        String kolcseId = clubService.addClub(kolcse).get().getEntityId();
        clubIds.add(kolcseId);

        ClubInfo nagydobosi = new ClubInfo("Nagydobosi Labdarugo Sport Egyesulet", "Nagydobosi LSE");
        String nagydobosiId = clubService.addClub(nagydobosi).get().getEntityId();
        clubIds.add(nagydobosiId);

        ClubInfo milota = new ClubInfo("Milota Sport Egyesulet", "Milota SE");
        String milotaId = clubService.addClub(milota).get().getEntityId();
        clubIds.add(milotaId);

        stageService.addClubsToStage(
                competitionId,
                stageId,
                clubIds)
                .get();

        createTurns(competitionId, Lists.from(clubIds.iterator()), stageId);
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

        stageService.addTurnsToStage(competitionId, stageId, turns);

        fillTurnWithRandomEvents(matchWithIds, competitionId, stageId);
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

            matchService.playMatch(matchInfo.getHomeClubId(),
                    matchInfo.getAwayClubId(),
                    competitionId(competitionId),
                    StageId.stageId(competitionId, UUID.fromString(stageId.toString())),
                    matchWithId.getEntityId(),
                    homeEvents,
                    awayEvents).get();
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
