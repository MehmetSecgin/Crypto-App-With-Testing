package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlphaVantageApi {

    @Autowired
    private AlphaVantageClient alphaVantageClient;

    @Autowired
    private RegexFind regexFind;

    LocalDate date = LocalDate.now();

    public MonthlyDataPoint queryForMonthly(String symbol, String market) {
        String body = alphaVantageClient.query(symbol, market);
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.has("Error Message")) {
            return new MonthlyDataPoint(jsonObject.getString("Error Message"), "", LocalDate.now(), 1.0, 0.0);
        } else if (jsonObject.has("Note")) {
            return new MonthlyDataPoint(jsonObject.getString("Note"), "", LocalDate.now(), 1.0, 0.0);
        } else if (jsonObject.toString().equals("{}")) {
        }

        JSONObject metaData = jsonObject.getJSONObject("Meta Data");
        JSONObject timeSeriesMonthly = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");
        MonthlyDataPoint returning = new MonthlyDataPoint();
        for (String key : timeSeriesMonthly.keySet()) {
            if (LocalDate.parse(key).equals(date)) {
                JSONObject dataPoint = timeSeriesMonthly.getJSONObject(key);
                List<String> matchListOpen = regexFind.getStrings(dataPoint, "([14][a]. [open]+ [(]\\w+[)])");
                List<String> matchListClose = regexFind.getStrings(dataPoint, "([14][a]. [close]+ [(]\\w+[)])");
                returning.setCurrencyName(metaData.getString("4. Market Code"));
                returning.setCoinName(metaData.getString("2. Digital Currency Code"));
                returning.setDate(LocalDate.parse(key));
                returning.setOpen(dataPoint.getDouble(matchListOpen.get(0)));
                returning.setClose(dataPoint.getDouble(matchListClose.get(0)));
                break;
            }

        }
        return returning;
    }


}
