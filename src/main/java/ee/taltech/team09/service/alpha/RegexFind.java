package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexFind {

    public String getStrings(JSONObject dataPoint, String regexPattern) {

        Pattern regex = Pattern.compile(regexPattern);
        Matcher regexMatcher = regex.matcher(dataPoint.toString());

        if (regexMatcher.find()) {
            return regexMatcher.group();
        }
        return null;
    }
}
