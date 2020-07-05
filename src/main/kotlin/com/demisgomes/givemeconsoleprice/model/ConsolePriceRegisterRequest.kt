package com.demisgomes.givemeconsoleprice.model

data class ConsolePriceRegisterRequest(
        val consoleName: String,
        val priceInUSD: Double,
        val profitPercentage: Double
)