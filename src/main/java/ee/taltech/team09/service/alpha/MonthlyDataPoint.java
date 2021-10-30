package ee.taltech.team09.service.alpha;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MonthlyDataPoint {
    private String currencyName;
    private String coinName;

    private LocalDate date;
    private Double open;
    private Double close;

    private String errorMessage;


    public MonthlyDataPoint(String currencyName, String coinName, LocalDate date, Double open, Double close) {
        this.currencyName = currencyName;
        this.coinName = coinName;
        this.date = date;
        this.open = open;
        this.close = close;
    }

    public MonthlyDataPoint(Double open, Double close) {
        this.open = open;
        this.close = close;
    }

    public MonthlyDataPoint() {
    }

    public MonthlyDataPoint(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
