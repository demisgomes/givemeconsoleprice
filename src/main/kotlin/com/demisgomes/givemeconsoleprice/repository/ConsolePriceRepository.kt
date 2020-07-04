package com.demisgomes.givemeconsoleprice.repository

import com.demisgomes.givemeconsoleprice.model.ConsolePrice
import org.springframework.data.jpa.repository.JpaRepository

interface ConsolePriceRepository : JpaRepository<ConsolePrice, Integer>{
    fun findByConsoleName(consoleName:String)
}