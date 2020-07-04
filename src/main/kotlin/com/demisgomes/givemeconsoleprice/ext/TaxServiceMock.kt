package com.demisgomes.givemeconsoleprice.ext

import com.demisgomes.givemeconsoleprice.service.TaxService
import org.springframework.stereotype.Service

@Service
class TaxServiceMock : TaxService{
    override fun getTaxPercentage(): Double {
        return 0.4
    }

}