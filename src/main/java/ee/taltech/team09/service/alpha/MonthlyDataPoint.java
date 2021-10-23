package ee.taltech.team09.service.alpha;

import java.time.LocalDate;

public class MonthlyDataPoint {
    private String currencyName;
    private String coinName;

    private LocalDate date;
    private Double open;
    private Double close;

    public MonthlyDataPoint(String currencyName,String coinName,LocalDate date, Double open, Double close) {
        this.currencyName = currencyName;
        this.coinName = coinName;
        this.date = date;
        this.open = open;
        this.close = close;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }
}
