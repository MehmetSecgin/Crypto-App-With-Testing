package ee.taltech.team09.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CryptoResult {

    private String currencyName;
    private String coinName;

    private LocalDate date;
    private Double open;
    private Double close;
    private Double change;

    private String errorMessage;

}
