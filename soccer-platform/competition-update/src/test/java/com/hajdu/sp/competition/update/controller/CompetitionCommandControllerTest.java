package com.hajdu.sp.competition.update.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.hajdu.sp.competition.update.BaseIntegrationTest;
import com.hajdu.sp.competition.update.config.AuthenticationServerConfig;
import com.hajdu.sp.competition.update.config.SecurityConfig;
import com.hajdu.sp.competition.update.config.UserDetailTestService;
import com.hajdu.sp.competition.update.command.competition.AddClub;
import com.hajdu.sp.competition.update.command.competition.AddSeason;
import com.hajdu.sp.competition.update.command.competition.AddStage;
import com.hajdu.sp.competition.update.command.competition.CreateCompetition;
import com.hajdu.sp.competition.update.service.CompetitionService;
import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.ids.CompetitionId;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.codec.binary.Base64;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hajdu.sp.competition.update.value.competition.ids.CompetitionId.competitionId;
import static com.hajdu.sp.competition.update.value.competition.ids.SeasonId.seasonId;
import static com.hajdu.sp.competition.update.value.competition.ids.StageId.stageId;
import static com.hajdu.sp.competition.update.value.competition.rule.SortingRule.*;
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

        String accessToken = obtainAccessToken();

        mvc.perform(post("/competition/")
                .header("Authorization", "Bearer " + accessToken)
                .content(objectMapper.writeValueAsString(competition))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSeason() throws Exception {
        String competitionId = competitionService.save(dummyCompetition()).get().getEntityId();
        AddSeason addSeason = dummySeason(competitionId(competitionId));

        mvc.perform(post("/competition/add/season")
                .content(objectMapper.writeValueAsString(addSeason))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddStageToSeason() throws Exception {
        String competitionId = competitionService.save(dummyCompetition()).get().getEntityId();
        AddSeason addSeason = dummySeason(competitionId(competitionId));
        competitionService.addSeason(addSeason).get().getEntityId();
        AddStage addStage = dummyStage(addSeason.getSeasonId());

        mvc.perform(post("/competition/add/stage")
                .content(objectMapper.writeValueAsString(addStage))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddClubsToStage() throws Exception {
        String competitionId = competitionService.save(dummyCompetition()).get().getEntityId();
        AddSeason addSeason = dummySeason(competitionId(competitionId));
        competitionService.addSeason(addSeason).get().getEntityId();
        AddStage addStage = dummyStage(addSeason.getSeasonId());
        competitionService.addStage(addStage);

        AddClub addClub = AddClub.builder()
                .stageId(addStage.getStageId())
                .build();

        TimeUnit.SECONDS.sleep(10);

        mvc.perform(post("/competition/add/clubs")
                .content(objectMapper.writeValueAsString(addClub))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private AddStage dummyStage(SeasonId seasonId) {
        return AddStage.builder()
                .name("First Season")
                .interval(Interval.from(LocalDateTime.now(), LocalDateTime.now().plusMonths(10)))
                .stageId(stageId(seasonId, UUID.randomUUID()))
                .stageRuleSet(
                        StageRuleSet.builder()
                                .numberOfTeams(10)
                                .sortingRules(Lists.newArrayList(GAMES_WON, GOAL_DIFFERENCE, GOAL_SCORED))
                                .build()
                )
                .build();
    }

    private AddSeason dummySeason(CompetitionId competitionId) {
        return AddSeason.builder()
                .seasonId(seasonId(competitionId, UUID.randomUUID()))
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

}
