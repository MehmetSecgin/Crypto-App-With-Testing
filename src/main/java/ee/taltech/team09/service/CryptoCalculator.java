package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCalculator {

    public CryptoResult calculate(List<MonthlyDataPoint> response) {
        return new CryptoResult();
    }
}
