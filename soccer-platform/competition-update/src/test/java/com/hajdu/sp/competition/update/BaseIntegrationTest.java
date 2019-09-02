package com.hajdu.sp.competition.update;

import com.hajdu.sp.competition.update.config.AuthenticationServerConfig;
import com.hajdu.sp.competition.update.config.SecurityConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.shaded.org.apache.commons.codec.binary.Base64;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = CompetitionUpdateApp.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {AuthenticationServerConfig.class, SecurityConfig.class})
public class BaseIntegrationTest {

//    @ClassRule
//    public static DockerComposeContainer compose = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
//            .withExposedService("cdcservice", 8099, Wait.forListeningPort());

    protected String obtainAccessToken() throws Exception {
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

    @Autowired
    protected MockMvc mvc;

}
