package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlphaVantageApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RegexFind regexFind;

    public List<MonthlyDataPoint> queryForMonthly() {
        ResponseEntity<String> entity = restTemplate.getForEntity("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&market=USD&apikey=demo", String.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            //todo smt
        }
        JSONObject jsonObject = new JSONObject(entity.getBody());
        if (jsonObject.has("Error Message")) {
            //todo smt
        }

        JSONObject timeSeriesMonthly = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");

        List<MonthlyDataPoint> dataPointList = new ArrayList<>();

        for (String key : timeSeriesMonthly.keySet()) {
            JSONObject dataPoint = timeSeriesMonthly.getJSONObject(key);
            List<String> matchList = regexFind.getStrings(dataPoint, "([14][a]. [\\w]+ [(]\\w+[)])");
            String currency = matchList.get(1).substring(10,13);
            dataPointList.add(new MonthlyDataPoint(
                    currency,
                    LocalDate.parse(key),
                    dataPoint.getDouble(matchList.get(1)),
                    dataPoint.getDouble(matchList.get(0)))
            );
        }
        return dataPointList;
    }


}
