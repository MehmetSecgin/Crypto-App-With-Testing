package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AlphaVantageApi {

    @Autowired
    private AlphaVantageClient alphaVantageClient;

    @Autowired
    private RegexFind regexFind;

    LocalDate date = LocalDate.now();

    public List<MonthlyDataPoint> queryForMonthly(String symbol, String market) {
//        ResponseEntity<String> entity = restTemplate.getForEntity("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&market=USD&apikey=demo", String.class);
        String body = alphaVantageClient.query(symbol, market);
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.has("Error Message")) {
            MonthlyDataPoint errorPoint = new MonthlyDataPoint(jsonObject.getString("Error Message"), LocalDate.now(), 1.0, 0.0);
            return List.of(errorPoint);
            //todo smt
        } else if (jsonObject.has("Note")) {
            MonthlyDataPoint errorPoint = new MonthlyDataPoint(jsonObject.getString("Note"), LocalDate.now(), 1.0, 0.0);
            return List.of(errorPoint);
        }

        JSONObject timeSeriesMonthly = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");
        List<MonthlyDataPoint> dataPointList = new ArrayList<>();
        for (String key : timeSeriesMonthly.keySet()) {
            if (LocalDate.parse(key).equals(date)) {
                JSONObject dataPoint = timeSeriesMonthly.getJSONObject(key);
                List<String> matchListOpen = regexFind.getStrings(dataPoint, "([14][a]. [open]+ [(]\\w+[)])");
                List<String> matchListClose = regexFind.getStrings(dataPoint, "([14][a]. [close]+ [(]\\w+[)])");
                String currency = matchListOpen.get(0).substring(10, 13);
                dataPointList.add(new MonthlyDataPoint(
                        currency,
                        LocalDate.parse(key),
                        dataPoint.getDouble(matchListOpen.get(0)),
                        dataPoint.getDouble(matchListClose.get(0)))
                );
                break;
            }

        }
        return dataPointList;
    }


}
