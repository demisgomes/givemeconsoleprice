package com.demisgomes.givemeconsoleprice.ext

import com.demisgomes.givemeconsoleprice.service.ExchangeRateService
import org.springframework.stereotype.Service

@Service
class ExchangeRateServiceMock: ExchangeRateService{
    override fun getExchangeRate(): Double {
        return 5.43
    }

}