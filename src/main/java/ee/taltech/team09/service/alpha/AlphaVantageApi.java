package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        MonthlyDataPoint monthlyDataPoint = new MonthlyDataPoint();
        if (jsonObject.has("Error Message")) {
            monthlyDataPoint.setErrorMessage(jsonObject.getString("Error Message"));
            return monthlyDataPoint;
        } else if (jsonObject.has("Note")) {
            monthlyDataPoint.setErrorMessage(jsonObject.getString("Note"));
            return monthlyDataPoint;
        }

        JSONObject metaData = jsonObject.getJSONObject("Meta Data");
        JSONObject timeSeriesMonthly = jsonObject.getJSONObject("Time Series (Digital Currency Monthly)");
        MonthlyDataPoint returning = new MonthlyDataPoint();
        for (String key : timeSeriesMonthly.keySet()) {
            if (LocalDate.parse(key).equals(date)) {
                JSONObject dataPoint = timeSeriesMonthly.getJSONObject(key);
                String matchOpen = regexFind.getStrings(dataPoint, "([14][a]. [open]+ [(]\\w+[)])");
                String matchClose = regexFind.getStrings(dataPoint, "([14][a]. [close]+ [(]\\w+[)])");
                returning.setCurrencyName(metaData.getString("4. Market Code"));
                returning.setCoinName(metaData.getString("2. Digital Currency Code"));
                returning.setDate(LocalDate.parse(key));
                returning.setOpen(dataPoint.getDouble(matchOpen));
                returning.setClose(dataPoint.getDouble(matchClose));
                break;
            }
        }
        return returning;
    }


}
