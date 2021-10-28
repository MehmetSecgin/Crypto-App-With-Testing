package ee.taltech.team09.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.team09.service.alpha.RegexFind;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@AutoConfigureMockMvc
public class RegexFindTest {

    @Autowired
    private RegexFind regexFind;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void RegexFindTestReturnsOpenAndCloseValue() throws IOException {
        String regexPattern1 = "([14][a]. [open]+ [(]\\w+[)])";
        String regexPattern2 = "([14][a]. [close]+ [(]\\w+[)])";
        JSONObject dataPoint = new JSONObject(testData("testOkReturn.json"));

        String result = regexFind.getStrings(dataPoint, regexPattern1);

        assertEquals("1a. open (EUR)", result);

    }

    @Test
    void RegexFindReturnsNullOnNoMatch() throws IOException{
        String regexPattern1 = "([14][a]. [x]+ [(]\\w+[)])";
        JSONObject dataPoint = new JSONObject(testData("testOkReturn.json"));
        String result = regexFind.getStrings(dataPoint, regexPattern1);

        assertNull(result);
    }

    public static String testData(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
