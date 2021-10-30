package ee.taltech.team09.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.AlphaVantageApi;
import ee.taltech.team09.service.alpha.AlphaVantageClient;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import ee.taltech.team09.service.api.RegexFindTest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import static org.assertj.core.api.Assertions.within;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AlphaVantageApi alphaVantageApi;

    @Test
    void cryptoControllerMockTest() throws Exception {
        Mockito.when(alphaVantageApi.queryForMonthly("BTC", "EUR")).thenReturn(
                new MonthlyDataPoint(
                        "EUR",
                        "BTC",
                        LocalDate.of(2020, 10, 10),
                        1.0,
                        1.0));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "BTC")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currencyName", is("EUR")))
                .andExpect(jsonPath("$[0].coinName", is("BTC")))
                .andExpect(jsonPath("$[0].date", is(LocalDate.of(2020, 10, 10).toString())))
                .andExpect(jsonPath("$[0].open", is(1.0)))
                .andExpect(jsonPath("$[0].close", is(1.0)))
                .andExpect(jsonPath("$[0].change", is(0.0)));
    }

    @Test
    void cryptoControllerMockTestV2() throws Exception {
        Mockito.when(alphaVantageApi.queryForMonthly("ETH", "EUR")).thenReturn(
                new MonthlyDataPoint(
                        "EUR",
                        "ETH",
                        LocalDate.of(2020, 10, 10),
                        1.0,
                        10.0));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "ETH")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CryptoResult> result = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(LocalDate.of(2020, 10, 10), result.get(0).getDate());
        assertThat(result.get(0).getOpen()).isCloseTo(1.0, within(0.0));
        assertThat(result.get(0).getClose()).isCloseTo(10.0, within(0.0));
        assertThat(result.get(0).getChange()).isCloseTo(900.0, within(0.0));

    }

    @Test
    void cryptoControllerMockTestReturnsNote() throws Exception {
        JSONObject dataPoint = new JSONObject(RegexFindTest.testData("testNOkNoteReturn.json"));
        MonthlyDataPoint NotePoint = new MonthlyDataPoint(dataPoint.getString("Note"), "", LocalDate.now(), 1.0, 0.0);
        Mockito.when(alphaVantageApi.queryForMonthly("ETH", "EUR")).thenReturn(NotePoint);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/crypto")
                        .param("symbol", "ETH")
                        .param("markets", "EUR"))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<CryptoResult> result = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(LocalDate.now(), result.get(0).getDate());
        assertThat(result.get(0).getOpen()).isCloseTo(1.0, within(0.0));
        assertThat(result.get(0).getClose()).isCloseTo(0.0, within(0.0));
        assertThat(result.get(0).getChange()).isCloseTo(-100.0, within(0.0));

    }

}
