package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(MonthlyDataPoint monthlyDataPoint) {
        Optional<MonthlyDataPoint> monthlyDataPointOptional = Optional.ofNullable(monthlyDataPoint);
        CryptoResult cryptoResult = new CryptoResult();
        if (monthlyDataPointOptional.isEmpty()) {
            return cryptoResult;
        }
        if (monthlyDataPoint.getErrorMessage() != null){
            cryptoResult.setErrorMessage(monthlyDataPoint.getErrorMessage());
            return cryptoResult;
        }
        cryptoResult.setCoinName(monthlyDataPoint.getCoinName());
        cryptoResult.setCurrencyName(monthlyDataPoint.getCurrencyName());
        cryptoResult.setDate(monthlyDataPoint.getDate());
        cryptoResult.setOpen(monthlyDataPoint.getOpen());
        cryptoResult.setClose(monthlyDataPoint.getClose());

        cryptoResult.setChange(calculateChange(monthlyDataPoint));

        return cryptoResult;
    }

    public Double calculateChange(MonthlyDataPoint monthlyDataPoint) {
        return monthlyDataPoint.getClose() * (100 / monthlyDataPoint.getOpen()) - 100;
    }

}
