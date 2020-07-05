package com.demisgomes.givemeconsoleprice.service

import com.demisgomes.givemeconsoleprice.model.ConsolePrice
import com.demisgomes.givemeconsoleprice.model.ConsolePriceCalculateRequest
import com.demisgomes.givemeconsoleprice.model.ConsolePriceRegisterRequest
import com.demisgomes.givemeconsoleprice.repository.ConsolePriceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsolePriceService(
        private val exchangeRateService: ExchangeRateService,
        private val taxService: TaxService,
        private val consolePriceRepository: ConsolePriceRepository){

    fun registerConsolePrice(consolePriceRegisterRequest: ConsolePriceRegisterRequest): ConsolePrice{
        val exchangeRate = exchangeRateService.getExchangeRate()
        val taxPercentage = taxService.getTaxPercentage()
        val priceInBRLBeforeTax = consolePriceRegisterRequest.priceInUSD * exchangeRate
        val taxAmount = priceInBRLBeforeTax * taxPercentage
        val profitAmount = (priceInBRLBeforeTax + taxAmount) * (consolePriceRegisterRequest.profitPercentage)

        val priceInBRL = priceInBRLBeforeTax + taxAmount + profitAmount

        val consolePrice =
                ConsolePrice(
                        consoleName = consolePriceRegisterRequest.consoleName,
                        priceInUSD = consolePriceRegisterRequest.priceInUSD,
                        exchangeRate = exchangeRate,
                        taxPercentage = taxPercentage,
                        taxAmount = taxAmount,
                        profitPercentage = consolePriceRegisterRequest.profitPercentage,
                        profitAmount = profitAmount,
                        priceInBRL = priceInBRL
                )

        return consolePriceRepository.save(consolePrice)
    }

    fun getConsolePriceById(id:Int): Optional<ConsolePrice> {
        return consolePriceRepository.findById(id)
    }

    fun calculateProfitFromBRL(consolePriceCalculateRequest: ConsolePriceCalculateRequest): ConsolePrice {
        val exchangeRate = exchangeRateService.getExchangeRate()
        val taxPercentage = taxService.getTaxPercentage()

        val priceInBRLBeforeTax = consolePriceCalculateRequest.priceInUSD * exchangeRate
        val taxAmount = priceInBRLBeforeTax * taxPercentage
        val priceInBRLWithTax = priceInBRLBeforeTax + taxAmount

        val profitAmount = consolePriceCalculateRequest.priceInBRL - (priceInBRLWithTax)
        val profitPercentage = profitAmount/(priceInBRLWithTax)

        return ConsolePrice(
                        consoleName = consolePriceCalculateRequest.consoleName,
                        priceInUSD = consolePriceCalculateRequest.priceInUSD,
                        exchangeRate = exchangeRate,
                        taxPercentage = taxPercentage,
                        taxAmount = taxAmount,
                        profitPercentage = profitPercentage,
                        profitAmount = profitAmount,
                        priceInBRL = consolePriceCalculateRequest.priceInBRL
                )
    }
}