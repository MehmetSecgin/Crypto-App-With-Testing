package ee.taltech.team09.controller;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/crypto")
@RestController
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping
    public List<CryptoResult> monthlyChange(
            @RequestParam(defaultValue = "BTC") String symbol,
            @RequestParam(defaultValue = "EUR,USD,AUD,CAD,JPY") List<String> markets) {
        List<CryptoResult> list = new ArrayList<>();

        for (String market : markets) {
            list.add(cryptoService.result(symbol, market));
        }
        return list;
    }
}
