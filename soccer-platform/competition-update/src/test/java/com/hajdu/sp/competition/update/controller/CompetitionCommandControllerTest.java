package com.hajdu.sp.competition.update.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hajdu.sp.competition.update.BaseIntegrationTest;
import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.service.CompetitionService;
import com.hajdu.sp.competition.update.util.SpDateTime;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.match.MatchId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.hajdu.sp.competition.update.DummyFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class CompetitionCommandControllerTest extends BaseIntegrationTest {
    private static final String BEARER = "Bearer ";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompetitionService competitionService;

    @Test
    public void testCreateCompetition() throws Exception {
        CreateCompetition competition = dummyCompetition();

        mvc.perform(post("/competitions/")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(competition))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSeason() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();

        mvc.perform(patch("/competitions/" + competitionId + "/seasons")
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

        mvc.perform(patch("/competitions/" + competitionId + "/stages")
                .header("Authorization", BEARER + obtainAccessToken())
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

        mvc.perform(patch("/competitions/" + competitionId + "/clubs")
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

        mvc.perform(patch("/competitions/" + competitionId + "/turns")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(List.of(addTurn)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMatches() throws Exception {
        String competitionId = competitionService.save(dummyCompetition());
        AddSeason addSeason = dummySeason();
        competitionService.addSeason(competitionId, addSeason);
        AddStage addStage = dummyStage(addSeason.getSeasonId());
        competitionService.addStages(competitionId, List.of(addStage));
        AddTurn addTurn = dummyTurn(addStage.getStageId());
        competitionService.addTurns(competitionId, List.of(addTurn));

        AddMatch addMatch = AddMatch.builder()
                .matchId(MatchId.matchId("123456"))
                .turnId(addTurn.getTurn().getTurnId())
                .dateTime(SpDateTime.spDateTime(LocalDateTime.now()))
                .build();

        mvc.perform(patch("/competitions/" + competitionId + "/matches")
                .header("Authorization", "Bearer " + obtainAccessToken())
                .content(objectMapper.writeValueAsString(List.of(addMatch)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
