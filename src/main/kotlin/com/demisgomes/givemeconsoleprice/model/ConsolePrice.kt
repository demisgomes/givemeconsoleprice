package com.demisgomes.givemeconsoleprice.model
import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ConsolePrice(
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val id: Int? = null,
        val consoleName: String,
        val priceInUSD: Double,
        val exchangeRate: Double,
        val taxPercentage: Double,
        val taxAmount: Double,
        val profitPercentage: Double,
        val profitAmount: Double,
        val priceInBRL: Double
)