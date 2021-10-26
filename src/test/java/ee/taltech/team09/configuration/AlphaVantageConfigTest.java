package ee.taltech.team09.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AlphaVantageConfigTest {

    @Autowired
    private AlphaVantageConfig config;
    @Test
    void AlphaVantageKeyTest(){
        assertEquals("DJ88371K8HCT55PW", config.getKey());
    }

    @Test
    void AlphaVantageURLTest(){
        assertEquals("https://www.alphavantage.co/query", config.getUrl());
    }

}
