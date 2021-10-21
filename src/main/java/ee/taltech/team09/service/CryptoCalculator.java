package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(List<MonthlyDataPoint> monthlyDataPoints) {
        Optional<MonthlyDataPoint> monthlyDataPointOptional = monthlyDataPoints.stream().max(Comparator.comparing(MonthlyDataPoint::getDate));
        if (monthlyDataPointOptional.isEmpty()) {
            return new CryptoResult();
        }
        MonthlyDataPoint monthlyDataPoint = monthlyDataPointOptional.get();
        CryptoResult cryptoResult = new CryptoResult();
        cryptoResult.setCurrencyName(monthlyDataPoint.getCurrencyName());
        cryptoResult.setDate(monthlyDataPoint.getDate());
        cryptoResult.setOpen(monthlyDataPoint.getOpen());
        cryptoResult.setClose(monthlyDataPoint.getClose());

        cryptoResult.setChange(calculateChange(monthlyDataPoint));

        return cryptoResult;
    }

    private Double calculateChange(MonthlyDataPoint monthlyDataPoint) {
        Double open = monthlyDataPoint.getOpen();
        return monthlyDataPoint.getClose()*(100/open)-100;
    }

}
