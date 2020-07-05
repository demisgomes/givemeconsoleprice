package com.demisgomes.givemeconsoleprice.integration

import com.demisgomes.givemeconsoleprice.service.TaxService
import org.springframework.stereotype.Service

@Service
class TaxServiceMock : TaxService{
    override fun getTaxPercentage(): Double {
        return 0.4
    }

}