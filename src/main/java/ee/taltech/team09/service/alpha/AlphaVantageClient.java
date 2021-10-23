package ee.taltech.team09.service.alpha;

import ee.taltech.team09.configuration.AlphaVantageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
public class AlphaVantageClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AlphaVantageConfig alphaVantageConfig;

    public String query(String symbol,String market) {
        String url = format("%s?function=DIGITAL_CURRENCY_MONTHLY&symbol=%s&market=%s&apikey=%s",
                alphaVantageConfig.getUrl(),
                symbol,
                market,
                alphaVantageConfig.getKey());
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (!entity.getStatusCode().is2xxSuccessful()){
            //todo do sth about it
        }
        return entity.getBody();
    }
}
