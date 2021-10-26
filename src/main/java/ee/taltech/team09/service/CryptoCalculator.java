package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(MonthlyDataPoint monthlyDataPoint) {
        Optional<MonthlyDataPoint> monthlyDataPointOptional = Optional.ofNullable(monthlyDataPoint);
        if (monthlyDataPointOptional.isEmpty()) {
            return new CryptoResult();
        }
        monthlyDataPoint = monthlyDataPointOptional.get();
        CryptoResult cryptoResult = new CryptoResult();
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
