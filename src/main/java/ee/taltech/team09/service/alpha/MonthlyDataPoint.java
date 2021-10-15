package ee.taltech.team09.service.alpha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyDataPoint {
    private LocalDate date;
    private BigDecimal open;
    private BigDecimal close;

    public MonthlyDataPoint(LocalDate date, BigDecimal open, BigDecimal close) {
        this.date = date;
        this.open = open;
        this.close = close;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }
}
