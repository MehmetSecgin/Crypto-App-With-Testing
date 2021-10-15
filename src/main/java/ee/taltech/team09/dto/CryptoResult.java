package ee.taltech.team09.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CryptoResult {

    private LocalDate date;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal change;

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

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }
}
