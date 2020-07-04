package com.demisgomes.givemeconsoleprice.model

data class ConsolePriceProfitRequest(
        val consoleName: String,
        val priceInUSD: Double,
        val profitPercentage: Double
)