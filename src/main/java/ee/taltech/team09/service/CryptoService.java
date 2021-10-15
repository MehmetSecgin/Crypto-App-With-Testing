package ee.taltech.team09.service;

import ee.taltech.team09.dto.CryptoResult;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    public CryptoResult result(){
        return new CryptoResult();
    }
}
