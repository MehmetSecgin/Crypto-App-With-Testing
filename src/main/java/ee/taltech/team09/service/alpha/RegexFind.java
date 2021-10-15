package ee.taltech.team09.service.alpha;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexFind {

    public List<String> getStrings(JSONObject dataPoint, String regexPattern) {

        List<String> matchList = new ArrayList<>();

        Pattern regex = Pattern.compile(regexPattern);
        Matcher regexMatcher = regex.matcher(dataPoint.toString());

        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        return matchList;
    }
}
