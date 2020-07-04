package com.demisgomes.givemeconsoleprice.service

import com.demisgomes.givemeconsoleprice.model.ConsolePrice
import com.demisgomes.givemeconsoleprice.model.ConsolePriceBRLRequest
import com.demisgomes.givemeconsoleprice.model.ConsolePriceProfitRequest
import com.demisgomes.givemeconsoleprice.repository.ConsolePriceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsolePriceService(
        private val exchangeRateService: ExchangeRateService,
        private val taxService: TaxService,
        private val consolePriceRepository: ConsolePriceRepository){

    fun registerConsolePrice(consolePriceProfitRequest: ConsolePriceProfitRequest): ConsolePrice{
        val exchangeRate = exchangeRateService.getExchangeRate()
        val taxPercentage = taxService.getTaxPercentage()
        val priceInBRLBeforeTax = consolePriceProfitRequest.priceInUSD * exchangeRate
        val taxAmount = priceInBRLBeforeTax * taxPercentage
        val profitAmount = (priceInBRLBeforeTax + taxAmount) * (consolePriceProfitRequest.profitPercentage)

        val priceInBRL = priceInBRLBeforeTax + taxAmount + profitAmount

        val consolePrice =
                ConsolePrice(
                        consoleName = consolePriceProfitRequest.consoleName,
                        priceInUSD = consolePriceProfitRequest.priceInUSD,
                        exchangeRate = exchangeRate,
                        taxPercentage = taxPercentage,
                        taxAmount = taxAmount,
                        profitPercentage = consolePriceProfitRequest.profitPercentage,
                        profitAmount = profitAmount,
                        priceInBRL = priceInBRL
                )

        return consolePriceRepository.save(consolePrice)
    }

    fun getConsolePriceById(id:Int): Optional<ConsolePrice> {
        return consolePriceRepository.findById(Integer(id))
    }

    fun calculateProfitFromBRL(consolePriceBRLRequest: ConsolePriceBRLRequest): ConsolePrice {
        val exchangeRate = exchangeRateService.getExchangeRate()
        val taxPercentage = taxService.getTaxPercentage()

        val priceInBRLBeforeTax = consolePriceBRLRequest.priceInUSD * exchangeRate
        val taxAmount = priceInBRLBeforeTax * taxPercentage

        val profitAmount = consolePriceBRLRequest.priceInBRL - (priceInBRLBeforeTax + taxAmount)

        val profitPercentage = profitAmount/consolePriceBRLRequest.priceInBRL

        return ConsolePrice(
                        consoleName = consolePriceBRLRequest.consoleName,
                        priceInUSD = consolePriceBRLRequest.priceInUSD,
                        exchangeRate = exchangeRate,
                        taxPercentage = taxPercentage,
                        taxAmount = taxAmount,
                        profitPercentage = profitPercentage,
                        profitAmount = profitAmount,
                        priceInBRL = consolePriceBRLRequest.priceInBRL
                )
    }
}