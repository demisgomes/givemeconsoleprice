package com.demisgomes.givemeconsoleprice.model

data class ConsolePriceBRLRequest(
        val consoleName: String,
        val priceInUSD: Double,
        val priceInBRL: Double
)