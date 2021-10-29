package ee.taltech.team09.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.AlphaVantageApi;
import ee.taltech.team09.service.alpha.AlphaVantageClient;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlphaVantageApiTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AlphaVantageApi alphaVantageApi;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AlphaVantageClient alphaVantageClient;

    @Test
    void QueryWithErrorNoteReturnsNote() throws Exception {
        JSONObject dataPoint = new JSONObject(RegexFindTest.testData("testNOkNoteReturn.json"));
        MonthlyDataPoint NotePoint = new MonthlyDataPoint(dataPoint.getString("Note"));
        when(alphaVantageClient.query("ETH", "EUR")).thenReturn(dataPoint.toString());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "ETH")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CryptoResult> result = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(result.get(0).getCurrencyName(),NotePoint.getOpen());
    }
    @Test
    void QueryWithErrorMessageReturnsErrorMessage() throws Exception {
        JSONObject dataPoint = new JSONObject(RegexFindTest.testData("testNOkErrorReturn.json"));
        MonthlyDataPoint NotePoint = new MonthlyDataPoint(dataPoint.getString("Error Message"));
        when(alphaVantageClient.query("ETH", "EUR")).thenReturn(dataPoint.toString());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "ETH")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CryptoResult> result = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(result.get(0).getCurrencyName(),NotePoint.getOpen());
    }
    @Test
    void QueryWithReturnsSuccessful() throws Exception {
        JSONObject dataPoint = new JSONObject(RegexFindTest.testData("testOkReturn.json"));
        MonthlyDataPoint NotePoint = new MonthlyDataPoint("EUR","ETH",LocalDate.of(2021,10,28),37785.99462300,50492.89916100);
        when(alphaVantageClient.query("ETH", "EUR")).thenReturn(dataPoint.toString());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "ETH")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CryptoResult> result = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(result.get(0).getCurrencyName(),NotePoint.getCurrencyName());
    }


}
