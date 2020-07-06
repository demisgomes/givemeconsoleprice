package com.demisgomes.givemeconsoleprice.controller

import com.demisgomes.givemeconsoleprice.model.ConsolePrice
import com.demisgomes.givemeconsoleprice.model.ConsolePriceCalculateRequest
import com.demisgomes.givemeconsoleprice.model.ConsolePriceRegisterRequest
import com.demisgomes.givemeconsoleprice.service.ConsolePriceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
class ConsolePriceController(private val consolePriceService: ConsolePriceService){
    @PostMapping("/consoles")
    fun registerConsolePrice(@RequestBody consolePriceRegisterRequest: ConsolePriceRegisterRequest): ResponseEntity<ConsolePrice>{
        val consolePrice = consolePriceService.registerConsolePrice(consolePriceRegisterRequest)
        val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consolePrice.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @GetMapping("/calculate")
    fun calculateProfit(@RequestBody consolePriceCalculateRequest: ConsolePriceCalculateRequest): ResponseEntity<ConsolePrice> {
        val consolePrice = consolePriceService.calculateProfitFromBRL(consolePriceCalculateRequest)
        return ResponseEntity.ok(consolePrice)
    }

    @GetMapping("/consoles/{id}")
    fun getConsolePriceByName(@PathVariable id:Int): ResponseEntity<ConsolePrice> {
        val consolePrice = consolePriceService.getConsolePriceById(id)
        return ResponseEntity.of(consolePrice)
    }




}