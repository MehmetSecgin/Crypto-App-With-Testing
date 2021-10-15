package ee.taltech.team09.controller;

import ee.taltech.team09.dto.CryptoResult;
import ee.taltech.team09.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/crypto")
@RestController
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping
    public CryptoResult monthlyChange(){
        return cryptoService.result();
    }
}
