package com.hajdu.sp.competition.update;

import com.hajdu.sp.competition.update.config.AuthenticationServerConfig;
import com.hajdu.sp.competition.update.config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CompetitionUpdateApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {AuthenticationServerConfig.class, SecurityConfig.class})
public class BaseIntegrationTest {

//    @ClassRule
//    public static DockerComposeContainer compose = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
//            .withExposedService("cdcservice", 8099, Wait.forListeningPort());

    @Autowired
    protected MockMvc mvc;

}
