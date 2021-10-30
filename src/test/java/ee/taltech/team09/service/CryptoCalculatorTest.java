package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.alpha.MonthlyDataPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class CryptoCalculatorTest {

    @Autowired
    private CryptoCalculator cryptoCalculator;

    @Test
    void calculateChange() {
        Double change = cryptoCalculator.calculateChange(new MonthlyDataPoint(1.0, 2.0));
        assertThat(change).isCloseTo(100.0, within(0.0));
    }

    @Test
    void calculateChangeV2() {
        Double change = cryptoCalculator.calculateChange(new MonthlyDataPoint(65784.57860, 65999.57860));
        assertThat(change).isCloseTo(0.5, within(0.2));
    }

    @Test
    void calculateChangeV3() {
        Double change = cryptoCalculator.calculateChange(new MonthlyDataPoint(3643.34, 3845.75));
        assertThat(change).isCloseTo(5.0, within(2.0));
    }

    @Test
    void calculateChangeV4() {
        Double change = cryptoCalculator.calculateChange(new MonthlyDataPoint(0.97, 1.74));
        assertThat(change).isCloseTo(100.0, within(50.0));
    }

    @Test
    void calculateChangeV5() {
        Double change = cryptoCalculator.calculateChange(new MonthlyDataPoint(2.45, 37.00));
        assertThat(change).isCloseTo(1500.0, within(1000.0));
    }

    @Test
    void calculateEmptyReturnsNewResult(){
        CryptoResult testResult = cryptoCalculator.calculate(null);
        assertThat(testResult).hasAllNullFieldsOrProperties();
    }
}
