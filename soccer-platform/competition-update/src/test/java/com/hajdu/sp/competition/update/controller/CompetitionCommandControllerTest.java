package com.hajdu.sp.competition.update.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hajdu.sp.competition.update.BaseIntegrationTest;
import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.service.CompetitionService;
import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.SeasonInfo;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import com.hajdu.sp.competition.update.value.match.MatchId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.codec.binary.Base64;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.hajdu.sp.competition.update.value.competition.ids.StageId.stageId;
import static com.hajdu.sp.competition.update.value.competition.rule.SortingRule.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompetitionCommandControllerTest extends BaseIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompetitionService competitionService;

    public String obtainAccessToken() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "dummyUser");
        params.add("password", "dummyPassword");
        params.add("scope", "read");

        String base64ClientCredentials = new String(Base64.encodeBase64(("oauth:oauth").getBytes()));

        ResultActions result = mvc.perform(post("/oauth/token")
                .params(params)
                .header("Authorization", "Basic " + base64ClientCredentials)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void testCreateCompetition() throws Exception {
        CreateCompetition competition = dummyCompetition();

        mvc.perform(post("/competition/")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(competition))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSeason() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();

        mvc.perform(patch("/competition/"+competitionId+"/seasons")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(addSeason))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddStage() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();
        competitionService.addSeason(competitionId, addSeason);
        AddStage addStage = dummyStage(addSeason.getSeasonId());

        mvc.perform(patch("/competition/"+competitionId+"/stages")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(List.of(addStage)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddClubsToStage() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();
        competitionService.addSeason(competitionId, addSeason);
        AddStage addStage = dummyStage(addSeason.getSeasonId());
        competitionService.addStages(competitionId, List.of(addStage));

        List<AddClub> addClub = List.of(AddClub.builder()
                .clubId(ClubId.clubId(UUID.randomUUID().toString()))
                .stageId(addStage.getStageId())
                .build());

        mvc.perform(patch("/competition/"+competitionId+"/clubs")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(addClub))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTurn() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();
        competitionService.addSeason(competitionId, addSeason);
        AddStage addStage = dummyStage(addSeason.getSeasonId());
        competitionService.addStages(competitionId, List.of(addStage));

        AddTurn addTurn = dummyTurn(addStage.getStageId());

        mvc.perform(patch("/competition/"+competitionId+"/turns")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(List.of(addTurn)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private AddStage dummyStage(SeasonId seasonId) {
        return AddStage.builder()
                .name("First Season")
                .interval(Interval.from(LocalDateTime.now(), LocalDateTime.now().plusMonths(10)))
                .stageId(stageId())
                .seasonId(seasonId)
                .stageRuleSet(
                        StageRuleSet.builder()
                                .numberOfTeams(10)
                                .sortingRules(List.of(GAMES_WON, GOAL_DIFFERENCE, GOAL_SCORED))
                                .build()
                )
                .build();
    }

    private AddSeason dummySeason() {
        return AddSeason.builder()
                .seasonId(SeasonId.seasonId())
                .seasonInfo(SeasonInfo.builder()
                        .description("Description")
                        .name("Competition")
                        .build())
                .interval(Interval.from(LocalDateTime.now(), LocalDateTime.now().plusMonths(6)))
                .build();
    }

    private CreateCompetition dummyCompetition() {
        return CreateCompetition.builder()
                .organizer(
                        Organizer.builder()
                                .email("email@email.com")
                                .name("Test Organizer")
                                .phoneNumber("123456789")
                                .build()
                )
                .build();
    }

    private AddTurn dummyTurn(StageId stageId) {
        return AddTurn.builder()
                .stageId(stageId)
                .turn(Turn.builder()
                        .interval(Interval.from(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1)))
                        .match(MatchId.matchId(UUID.randomUUID().toString()))
                        .turnId(TurnId.turnId(0))
                        .build())
                .build();
    }

}
