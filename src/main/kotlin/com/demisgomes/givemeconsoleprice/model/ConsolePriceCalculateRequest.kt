package com.demisgomes.givemeconsoleprice.model

data class ConsolePriceCalculateRequest(
        val consoleName: String,
        val priceInUSD: Double,
        val priceInBRL: Double
)