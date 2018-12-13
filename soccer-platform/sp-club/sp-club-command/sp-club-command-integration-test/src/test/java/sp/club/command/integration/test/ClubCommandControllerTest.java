package sp.club.command.integration.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.club.api.command.CreateClub;
import com.sp.club.api.value.ClubInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sp.club.command.aggregator.service.ClubService;
import sp.club.command.app.ClubCommandApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ClubCommandApp.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClubCommandControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClubService competitionService;

    @Test
    public void testCreateCompetition() throws Exception {
        CreateClub createClub = dummyClub();

        mvc.perform(post("/club/")
                .content(objectMapper.writeValueAsString(createClub))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private CreateClub dummyClub() {
        return CreateClub.builder()
                .clubInfo(ClubInfo.builder()
                        .name("Club")
                        .fullName("Club FC")
                        .build())
                .build();
    }
}
