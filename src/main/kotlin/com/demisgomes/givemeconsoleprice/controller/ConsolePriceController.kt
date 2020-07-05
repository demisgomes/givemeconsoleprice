package com.demisgomes.givemeconsoleprice.controller

import com.demisgomes.givemeconsoleprice.model.ConsolePrice
import com.demisgomes.givemeconsoleprice.model.ConsolePriceBRLRequest
import com.demisgomes.givemeconsoleprice.model.ConsolePriceProfitRequest
import com.demisgomes.givemeconsoleprice.service.ConsolePriceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Optional


@RestController
class ConsolePriceController(private val consolePriceService: ConsolePriceService){
    @PostMapping("/consoles")
    fun registerConsolePrice(@RequestBody consolePriceProfitRequest: ConsolePriceProfitRequest): ResponseEntity<ConsolePrice>{
        val consolePrice = consolePriceService.registerConsolePrice(consolePriceProfitRequest)
        val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consolePrice.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/profit/calculate")
    fun calculateProfit(@RequestBody consolePriceBRLRequest: ConsolePriceBRLRequest): ResponseEntity<ConsolePrice> {
        val consolePrice = consolePriceService.calculateProfitFromBRL(consolePriceBRLRequest)
        return ResponseEntity.ok(consolePrice)
    }

    @GetMapping("/consoles/{id}")
    fun getConsolePriceByName(@PathVariable id:Int): ResponseEntity<ConsolePrice> {
        val consolePrice = consolePriceService.getConsolePriceById(id)
        return ResponseEntity.of(consolePrice)
    }




}