package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AlphaVantageApi {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RegexFind regexFind;

    public List<MonthlyDataPoint> queryForMonthly() {
        ResponseEntity<String> entity = restTemplate.getForEntity("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&market=EUR&apikey=demo ", String.class);
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
            dataPointList.add(new MonthlyDataPoint(
                    LocalDate.parse(key),
                    dataPoint.getBigDecimal(matchList.get(1)),
                    dataPoint.getBigDecimal(matchList.get(0)))
            );
        }
        return dataPointList;
    }


}
