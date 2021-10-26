package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.AlphaVantageApi;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {
    @Autowired
    private CryptoCalculator cryptoCalculator;

    @Autowired
    private AlphaVantageApi alphaVantageApi;


    public CryptoResult result(String symbol,String market) {
        MonthlyDataPoint response = alphaVantageApi.queryForMonthly(symbol,market);
        return cryptoCalculator.calculate(response);
    }


}
