package com.hajdu.sp.competition.update.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.hajdu.sp.common.Interval;
import com.hajdu.sp.competition.lib.command.AddClubs;
import com.hajdu.sp.competition.lib.command.AddSeason;
import com.hajdu.sp.competition.lib.command.AddStage;
import com.hajdu.sp.competition.lib.command.CreateCompetition;
import com.hajdu.sp.competition.lib.value.CompetitionId;
import com.hajdu.sp.competition.lib.value.CompetitionInfo;
import com.hajdu.sp.competition.lib.value.SeasonId;
import com.hajdu.sp.competition.lib.value.rule.StageRuleSet;
import com.hajdu.sp.competition.update.CompetitionUpdateApp;
import com.hajdu.sp.competition.update.SpringSecurityWebAuxTestConfig;
import com.hajdu.sp.competition.update.service.CompetitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hajdu.sp.competition.lib.value.CompetitionId.competitionId;
import static com.hajdu.sp.competition.lib.value.SeasonId.seasonId;
import static com.hajdu.sp.competition.lib.value.StageId.stageId;
import static com.hajdu.sp.competition.lib.value.rule.SortingRule.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
public class CompetitionCommandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompetitionService competitionService;

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateCompetition() throws Exception {
        CreateCompetition competition = dummyCompetition();

        mvc.perform(post("/competition/")
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

        AddClubs addClubs = AddClubs.builder()
                .stageId(addStage.getStageId())
                .build();

        TimeUnit.SECONDS.sleep(10);

        mvc.perform(post("/competition/add/clubs")
                .content(objectMapper.writeValueAsString(addClubs))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private AddStage dummyStage(SeasonId seasonId) {
        return AddStage.builder()
                .name("First Season")
                .interval(Interval.from(LocalDate.now(), LocalDate.now().plusMonths(10)))
                .stageId(stageId(seasonId, UUID.randomUUID()))
                .stageRuleSet(StageRuleSet
                        .builder()
                        .numberOfTeams(10)
                        .sortingRules(Lists.newArrayList(GAMES_WON, GOAL_DIFFERENCE, GOAL_SCORED))
                        .build())
                .build();
    }

    private AddSeason dummySeason(CompetitionId competitionId) {
        return AddSeason.builder()
                .name("First Season")
                .interval(Interval.from(LocalDate.now(), LocalDate.now().plusMonths(10)))
                .seasonId(seasonId(competitionId, UUID.randomUUID()))
                .build();
    }

    private CreateCompetition dummyCompetition() {
        return CreateCompetition.builder()
                .competitionInfo(
                        CompetitionInfo
                                .builder()
                                .name("First Competition")
                                .description("Description")
                                .interval(Interval.from(LocalDate.now(), LocalDate.now().plusMonths(10)))
                                .build())
                .build();
    }

}